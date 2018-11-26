import java.util.Iterator;


import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class TestWebserviceClientAxiom 
{
	private static EndpointReference targetEPR = 
		new EndpointReference(
		"http://localhost:8080/empservice/services/empservice");

	public static OMElement getPricePayload( )
	{
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(
				"http://service.emp/xsd", "tns");

		OMElement method = fac.createOMElement("getEmployeeInfo", omNs);

		OMElement value11 = fac.createOMElement("Employee", omNs);
		value11.addChild(fac.createOMText(value11, "123"));
		method.addChild(value11);

		OMElement value1 = fac.createOMElement("id", omNs);
		value1.addChild(fac.createOMText(value1, "123"));
		value11.addChild(value1);

		OMElement value2 = fac.createOMElement("name", omNs);
		value2.addChild(fac.createOMText(value2, "Hati"));
		value11.addChild(value2);

		OMElement value3 = fac.createOMElement("salary", omNs);
		value3.addChild(fac.createOMText(value3, "123.45"));
		value11.addChild(value3);

		method.addChild(value11);

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
		System.out.println(result.getQName());

		Iterator itr = result.getChildElements();
		Iterator innerItr;
		while( itr.hasNext() )
		{
			OMElement result11 = (OMElement) itr.next();
			innerItr = result11.getChildElements();

			while( innerItr.hasNext() )
			{
				OMElement e = (OMElement) innerItr.next();
				System.out.println(e.getLocalName()+"-----------"+e.getText());
//				System.out.println(((OMElement) innerItr.next()).getText());
			}

		}


	}
}
