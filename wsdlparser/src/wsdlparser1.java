import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.QName;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import org.apache.axis.encoding.DefaultTypeMappingImpl;
import org.apache.axis.encoding.TypeMapping;

import com.ibm.wsdl.ImportImpl;
import com.ibm.wsdl.factory.WSDLFactoryImpl;


public class wsdlparser1 
{
	public void parseWSDL(String implURI) throws WSDLException 
	{

		
		Definition implDef = null;
		Definition interfaceDef = null;
		String targetNamespace = null;
		String serviceName = null;
		String portName = null;
		String operationName = null;
		Object[] inputParams = null;

		// first get the definition object got the WSDL impl
		try {
		    WSDLFactory factory = new WSDLFactoryImpl();
		    WSDLReader reader = factory.newWSDLReader();
		    implDef = reader.readWSDL(implURI);
		} catch(WSDLException e) {
		    e.printStackTrace();
		}

		if(implDef==null) {
		    throw new WSDLException(WSDLException.OTHER_ERROR,"No WSDL impl definition found.");
		}

		// now get the Definition object for the interface WSDL
		Map imports = implDef.getImports();
		Set s = imports.keySet();
		Iterator it = s.iterator();
		while(it.hasNext()) {
		    Object o = it.next();
		    Vector intDoc = (Vector)imports.get(o);
		    // we want to get the ImportImpl object of it exists
		    for(int i=0; i<intDoc.size(); i++) {
			Object obj = intDoc.elementAt(i);
			if(obj instanceof ImportImpl) {
			    interfaceDef = ((ImportImpl)obj).getDefinition();
			}
		    }
		}

		if(interfaceDef == null) 
		{
		    throw new WSDLException(WSDLException.OTHER_ERROR,"No WSDL interface definition found.");
		}

		// let's get the target namespace Axis will need from the WSDL impl
		targetNamespace = implDef.getTargetNamespace();

		// great we've got the WSDL definitions now we need to find the PortType so
		// we can find the methods we can invoke
		Vector allPorts = new Vector();
	        Map ports = interfaceDef.getPortTypes();
		s = ports.keySet();
		it = s.iterator();
		while(it.hasNext()) {
		    Object o = it.next();
		    Object obj = ports.get(o);
		    if(obj instanceof PortType) {
			allPorts.add((PortType)obj);
		    }
		}	

		// now we've got a vector of all the port types - normally some logic would
		// go here to choose which port type we want to use but we'll just choose 
		// the first one
		PortType port = (PortType)allPorts.elementAt(0);
		List operations = port.getOperations();

		// let's get the service in the WSDL impl which contains this port
		// to do this we must first find the QName of the binding with the port type 
		// that corresponds to the port type of our chosen part
		QName bindingQName = null;
		Map bindings = interfaceDef.getBindings();
		s = bindings.keySet();
		it = s.iterator();
		while(it.hasNext()) {
		    Binding binding = (Binding)bindings.get(it.next());
		    if(binding.getPortType()==port) {
			// we've got our binding
			bindingQName = binding.getQName();
		    }
		}

		if(bindingQName==null) {
		    throw new WSDLException(WSDLException.OTHER_ERROR,"No binding found for chosen port type.");         
		}

		// now we can find the service in the WSDL impl which provides an endpoint
		// for the service we just found above
		Map implServices = implDef.getServices();
		s = implServices.keySet();
		it = s.iterator();
		while(it.hasNext()) {
		    Service serv = (Service)implServices.get(it.next());
		    Map m = serv.getPorts();
		    Set set = m.keySet(); 
		    Iterator iter = set.iterator();
		    while(iter.hasNext()) {
			Port p = (Port)m.get(iter.next());
			if(p.getBinding().getQName().toString().equals(bindingQName.toString())) {
			    // we've got our service store the port name and service name
			    portName = serv.getQName().toString();
			    serviceName = p.getName();
			    break;
			}
		    } 
		    if(portName != null) break;
		}
		
		// ok now we got all the operations previously - normally we would have some logic here to
		// choose which operation, however, for the sake of simplicity we'll just 
		// choose the first one
		Operation op = (Operation)operations.get(0);
		operationName = op.getName();



		// now let's get the Message object describing the XML for the input and output
		// we don't care about the specific type of the output as we'll just cast it to an Object
		Message inputs = op.getInput().getMessage();

		// let's find the input params 
		Map inputParts = inputs.getParts();
		// create the object array which Axis will use to pass in the parameters
		inputParams = new Object[inputParts.size()];
		s = inputParts.keySet();
		it = s.iterator();
		int i=0;
		while(it.hasNext()) {
		    Part part = (Part)inputParts.get(it.next());
		    QName qname = part.getTypeName();

		    // if it's not in the http://www.w3.org/2001/XMLSchema namespace then
		    // we don't know about it - throw an exception
		    String namespace = qname.getNamespaceURI();
		    if(!namespace.equals("http://www.w3.org/2001/XMLSchema")) {
			throw new WSDLException(WSDLException.OTHER_ERROR,"Namespace unrecognized");
		    }

		    // now we can get the Java type which the the QName maps to - we do this
		    // by using the Axis tools which map WSDL types to Java types in the wsdl2java tool
		    String localPart = qname.getLocalPart();
		    javax.xml.rpc.namespace.QName wsdlQName = new javax.xml.rpc.namespace.QName(namespace,localPart);
		    TypeMapping tm = DefaultTypeMappingImpl.create();
		    Class cl = tm.getClassForQName(wsdlQName);

		    // if the Java type is a primitive, we need to wrap it in an object
		    if(cl.isPrimitive()) {
			cl = wrapPrimitive(cl); 
		    }

		    // we could prompt the user to input the param here but we'll just
		    // assume a random number between 1 and 10
		    // first we need to find the constructor which takes a string representation of a number
		    // if a complex type was required we would use reflection to break it down
		    // and prompt the user to input values for each member variable in Object representing
		    // the complex type
		    try {
			Constructor cstr = cl.getConstructor(new Class[] { Class.forName("java.lang.String") });
			inputParams[i] = cstr.newInstance(new Object [] { ""+new Random().nextInt(10) });
		    } catch(Exception e) {
			// shoudn't happen
			e.printStackTrace();
		    }
		    i++;
		}	

		// great now we've built up all the paramters we need to invoke the Web service with Axis
		// now all we need to do is actually invoke it

		System.out.print("\nAxis parameters gathered:\nTargetNamespace = "+targetNamespace +"\n"+
		    "Service Name = "+serviceName +"\n"+
		    "Port Name = "+portName +"\n"+
		    "Operation Name = "+operationName+"\n"+
		    "Input Parameters = ");
		for(i=0; i<inputParams.length; i++) {
		    System.out.print(inputParams[i]);
		    if(inputParams.length != 0 && inputParams.length-1 > i) {
			System.out.print(", ");
		    }
		}
		System.out.println("\n");

//		axisInvoke(targetNamespace, serviceName, portName, operationName, inputParams, implURI);
	    
	}
	
	public Class wrapPrimitive(Class cl) throws WSDLException {
		String type = cl.getName();
		try {
		    if(type.equals("byte")) {
			return Class.forName("java.lang.Byte");
		    } else if(type.equals("char")) {
			return Class.forName("java.lang.Character");
		    } else if(type.equals("short")) {
			return Class.forName("java.lang.Short");
		    } else if(type.equals("int")) {
			return Class.forName("java.lang.Integer");
		    } else if(type.equals("double")) {
			return Class.forName("java.lang.Double");
		    } else if(type.equals("float")) {
			return Class.forName("java.lang.Float");
		    } else if(type.equals("long")) {
			return Class.forName("java.lang.Long"); 
		    } else {
			throw new WSDLException(WSDLException.OTHER_ERROR,"Unrecognized primitive type");
		    }
		} catch(ClassNotFoundException e) {
		    // this should never happen
		    e.printStackTrace();
		    return null;
		}

	    }

}
