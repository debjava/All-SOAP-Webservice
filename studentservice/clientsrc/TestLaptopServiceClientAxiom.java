import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class TestLaptopServiceClientAxiom 
{
	private static String webserviceURL = "http://localhost:8080/studentservice/services/studentservice";
	private static EndpointReference targetEPR = new EndpointReference( webserviceURL );
	
	public static OMElement getPricePayload( )
	{
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.student/xsd", "tns");

		OMElement method = fac.createOMElement("getExamResult", omNs);

		OMElement value11 = fac.createOMElement("Student", omNs);

		OMElement value1 = fac.createOMElement("name", omNs);
		value1.addChild(fac.createOMText(value1, "My Name"));
		value11.addChild(value1);

		OMElement value2 = fac.createOMElement("rollNo", omNs);
		value2.addChild(fac.createOMText(value2, "320"));
		value11.addChild(value2);
		
		method.addChild(value11);
		
		//Another 
		OMElement value12 = fac.createOMElement("Student", omNs);

		OMElement value3 = fac.createOMElement("name", omNs);
		value3.addChild(fac.createOMText(value3, "Hati"));
		value12.addChild(value3);

		OMElement value4 = fac.createOMElement("rollNo", omNs);
		value4.addChild(fac.createOMText(value4, "33"));
		value12.addChild(value4);
		
		method.addChild(value12);
		

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
			innerItr = result11.getChildElements();
			System.out.println("-------------- Details Name -------------");
			while( innerItr.hasNext() )
			{
				OMElement e = (OMElement) innerItr.next();
				System.out.println(e.getLocalName()+"-----------"+e.getText());
//				System.out.println(((OMElement) innerItr.next()).getText());
			}

		}
	}

}
