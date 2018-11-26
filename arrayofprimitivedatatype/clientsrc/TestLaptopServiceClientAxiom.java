import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class TestLaptopServiceClientAxiom 
{
	private static String webserviceURL = "http://localhost:8080/arrayofprimitivedatatypeservice/services/arrayofprimitivedatatypeservice";
	private static EndpointReference targetEPR = new EndpointReference( webserviceURL );
	
	public static OMElement getPricePayload( )
	{
		
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.arrayofprimitivedatatype/xsd", "tns");

		OMElement method = fac.createOMElement("getData", omNs);

		OMElement value11 = fac.createOMElement("datas", omNs);
		value11.addChild(fac.createOMText(value11, "123"));
		method.addChild(value11);

		OMElement value1 = fac.createOMElement("datas", omNs);
		value1.addChild(fac.createOMText(value1, "11"));
		value11.addChild(value1);
		method.addChild(value1);
		
		OMElement value2 = fac.createOMElement("datas", omNs);
		value2.addChild(fac.createOMText(value2, "320"));
		value11.addChild(value2);
		method.addChild(value2);
		
		OMElement value3 = fac.createOMElement("datas", omNs);
		value3.addChild(fac.createOMText(value3, "33"));
		value11.addChild(value3);
		method.addChild(value3);
		
//		OMElement value4 = fac.createOMElement("osName", omNs);
//		value4.addChild(fac.createOMText(value4, "Windows"));
//		value11.addChild(value4);
//		
//		OMElement value5 = fac.createOMElement("priceRange", omNs);
//		value5.addChild(fac.createOMText(value5, "1234.55"));
//		value11.addChild(value5);
//		
//		OMElement value6 = fac.createOMElement("ramSize", omNs);
//		value6.addChild(fac.createOMText(value6, "3"));
//		value11.addChild(value6);
		
//		method.addChild(value11);

		return method;
	}

	
	public static void main(String[] args) throws Exception 
	{
		OMElement getPricePayload = getPricePayload( );

		Options options = new Options();
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMElement result = sender.sendReceive(getPricePayload);
//		System.out.println(result.getQName());

		Iterator itr = result.getChildElements();
		Iterator innerItr;
		while( itr.hasNext() )
		{
			OMElement result11 = (OMElement) itr.next();
			System.out.println(result11.getLocalName()+"-----------"+result11.getText());
//			innerItr = result11.getChildElements();
//			System.out.println("-------------- Details Name -------------");
//			while( innerItr.hasNext() )
//			{
//				OMElement e = (OMElement) innerItr.next();
//				System.out.println(e.getLocalName()+"-----------"+e.getText());
////				System.out.println(((OMElement) innerItr.next()).getText());
//			}

		}
	}

}
