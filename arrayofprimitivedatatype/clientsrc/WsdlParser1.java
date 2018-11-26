import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Output;

import com.ibm.wsdl.OperationImpl;
import com.ibm.wsdl.PartImpl;
import com.ibm.wsdl.PortTypeImpl;
import com.ibm.wsdl.ServiceImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;


public class WsdlParser1 
{
	private static String webserviceURL = "http://localhost:8080/alldatatypeservice/services/datatypeservice?wsdl";
//	public static void showList(List list)
//	{
//		
//	}
	public static void main(String[] args) 
	{
		try 
		{
			WSDLReaderImpl reader = new WSDLReaderImpl();
			Definition defn = reader.readWSDL(webserviceURL);
			Map map = defn.getPortTypes();
			
			Iterator itr = map.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry )itr.next();
//				System.out.println(me.getKey().getClass().getName()+"------"+me.getValue().getClass().getName());
				
				PortTypeImpl impl = (PortTypeImpl)me.getValue();
//				System.out.println(impl.getOperations());
				
				List opList = impl.getOperations();
				for( int i = 0 ; i < opList.size() ; i++ )
				{
					OperationImpl opImpl = (OperationImpl)opList.get(i);
					System.out.println("Operation Name:::"+opImpl.getName());
//					System.out.println(opImpl.getExtensionAttributes());
//					System.out.println("Input Name:::"+opImpl.getInput().getMessage());
//					System.out.println(opImpl.getExtensibilityElements());
//					System.out.println(opImpl.getExtensionAttributes());
//					System.out.println(opImpl.getNativeAttributeNames());
					
					
					
					Message msg = opImpl.getInput().getMessage();
					
					Map partMap = msg.getParts();
					
					Iterator itr1 = partMap.entrySet().iterator();
					while( itr1.hasNext() )
					{
						Map.Entry me1 = (Map.Entry )itr1.next();
//						System.out.println(me1.getKey()+"-----"+me1.getValue());
						PartImpl pi = (PartImpl)me1.getValue();
						System.out.println(pi.getName());
//						System.out.println(pi.get);
						
//						List piList = pi.getNativeAttributeNames();
//						for( int j = 0 ; j < piList.size() ; j++ )
//						{
//							System.out.println(piList.get(j));
//						}
						
						
//						
					}
					
					
					
//					System.out.println(msg.getParts());
//					System.out.println("Output Name:::"+opImpl.getOutput());
//					Output out = opImpl.getOutput();
//					System.out.println(out.getMessage());
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
