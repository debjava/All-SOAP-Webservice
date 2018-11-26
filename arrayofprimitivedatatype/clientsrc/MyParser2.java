import javax.wsdl.Types;

import org.apache.axis2.jaxws.wsdl.impl.SchemaReaderImpl;
import org.apache.axis2.wsdl.WSDLUtil;
import org.apache.woden.WSDLFactory;
import org.apache.woden.WSDLReader;
import org.apache.woden.wsdl20.Description;
import org.apache.woden.wsdl20.Service;

import com.sun.xml.internal.ws.wsdl.parser.ParserUtil;


public class MyParser2 
{
	private static String webserviceURL = "http://localhost:8080/alldatatypeservice/services/datatypeservice?wsdl";
	
	public static void main(String[] args) throws Exception
	{
		
		Types types = javax.wsdl.factory.WSDLFactory.newInstance().newWSDLReader().readWSDL(webserviceURL).getTypes();
		System.out.println(types.getExtensionAttributes());
		
//		org.apache.axis2.wsdl.codegen.schema.
//		
//		SchemaReaderImpl impl = new SchemaReaderImpl();
		
		
//		new org.apache.axis2.jaxws.wsdl.impl.SchemaReaderImpl();
//		WSDLReader r = WSDLFactory.newInstance().newWSDLReader();
//		Description desc = r.readWSDL(webserviceURL);
//		Service[] serv = desc.getServices();
//		
//		for( int i = 0 ; i < serv.length ; i++ )
//		{
//			System.out.println(serv[i]);
//		}
		
		
		
		
		
	}
}
