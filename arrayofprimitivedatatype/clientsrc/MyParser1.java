import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.PortType;
import javax.wsdl.Types;
import javax.xml.namespace.QName;

import org.apache.axis2.util.WSDL20Util;
import org.apache.axis2.wsdl.WSDLUtil;
import org.apache.woden.WSDLFactory;
import org.apache.woden.WSDLReader;
import org.apache.woden.internal.BaseWSDLReader;

import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.BindingOperationImpl;
import com.ibm.wsdl.MessageImpl;
import com.ibm.wsdl.OperationImpl;
import com.ibm.wsdl.PartImpl;
import com.ibm.wsdl.PortTypeImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;




public class MyParser1 
{
	private static String webserviceURL = "http://localhost:8080/alldatatypeservice/services/datatypeservice?wsdl";
	
	private static void showMap( Map map )
	{
		Iterator itr = map.entrySet().iterator();
		while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry )itr.next();
//				System.out.println(me.getKey()+"-----"+me.getValue());
//				System.out.println(me.getKey().getClass()+"-----"+me.getValue().getClass());
				
				PortTypeImpl pi = (PortTypeImpl)me.getValue();
				List l = pi.getOperations();
				for( int i = 0 ; i < l.size() ; i++ )
				{
					OperationImpl oi = (OperationImpl)l.get(i);
					Message msg = oi.getInput().getMessage();
					System.out.println(msg.getParts());
				}
//				MessageImpl mi = (MessageImpl)me.getValue();
//				System.out.println(mi.getParts());
				
			}	
	}
	
	
	public static void main(String[] args) throws Exception
	{
	
		
		
		
		
		
		
		WSDLReaderImpl reader = new WSDLReaderImpl();
		Definition defn = reader.readWSDL(webserviceURL);
//		Map pMap = defn.getPortTypes();
//		showMap(pMap);
		
		
		
		
		Types types = defn.getTypes();
		System.out.println(types.getDocumentationElement());
//		
//		Map msgMap = defn.getMessages();
////		showMap(msgMap);
//		
//		Map bmap = defn.getBindings();
////		showMap(bmap);
//		
//		Map pmap = defn.getPortTypes();
//		showMap(pmap);
		
		
		
		
//		Map map = defn.getBindings();
//		Iterator itr = map.entrySet().iterator();
//		while( itr.hasNext() )
//		{
//			Map.Entry me = (Map.Entry )itr.next();
//			
//			BindingImpl bi = (BindingImpl)me.getValue();
//			
//			List l1 = bi.getBindingOperations();
//			for( int i = 0 ; i < l1.size() ; i++ )
//			{
//				BindingOperationImpl boi = (BindingOperationImpl)l1.get(i);
////				System.out.println(boi.getOperation().getName());
//				
//				Operation op = boi.getOperation();
//				System.out.println(op.getNativeAttributeNames());
////				Map m = boi.getOperation().getInput().getMessage().getParts();
////				
////				Iterator itr1 = m.entrySet().iterator();
////				while( itr1.hasNext() )
////				{
////					Map.Entry me1 = (Map.Entry)itr1.next();
////					PartImpl pi = (PartImpl)me1.getValue();
////					System.out.println(pi.getNativeAttributeNames());
//////					System.out.println(me1.getKey()+"------"+me1.getValue().getClass());
////				}
//				
//			}
//			
//			
//			
//		}	
		
	}

}
