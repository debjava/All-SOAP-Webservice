import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axiom.om.impl.traverse.OMChildrenIterator;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class TestClient1 {

	
	public static OMElement getPricePayload(String symbol) 
	{
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                                                 "http://service.astro/xsd", "ns1");

        OMElement method = fac.createOMElement("getZodiacSignList", omNs);
//        OMElement value = fac.createOMElement("symbol", omNs);
//        value.addChild(fac.createOMText(value, symbol));
//        method.addChild(value);
        return method;
    }
	
	public static OMElement getPricePayload1(String symbol) 
	{
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                                                 "http://service.astro/xsd", "ns1");

        OMElement method = fac.createOMElement("getZodiacSign", omNs);
        
        
        OMElement value1 = fac.createOMElement("day", omNs);
        value1.addChild(fac.createOMText(value1, "27"));
        method.addChild(value1);
        
        OMElement value2 = fac.createOMElement("month", omNs);
        value2.addChild(fac.createOMText(value1, "07"));
        method.addChild(value2);
        
        OMElement value3 = fac.createOMElement("year", omNs);
        value3.addChild(fac.createOMText(value3, "1978"));
        method.addChild(value3);
        
        
        return method;
    }
	
	
	public static void main(String[] args) 
	{
		String endPoint = "http://localhost:8080/astroservice/services/Zodiac?wsdl";

		EndpointReference targetEPR = 
	        new EndpointReference(
	                              "http://localhost:8080/astroservice/services/Zodiac");
		
		
		try
		{
			OMElement getPricePayload = getPricePayload1("WSO");
			Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);

            OMElement result = sender.sendReceive(getPricePayload);

//            String response = result.getFirstElement().getText();
            
            OMChildrenIterator itr = (OMChildrenIterator)result.getChildren();//getChildElements();
            while( itr.hasNext() )
            {
            	OMElementImpl impl = (OMElementImpl)itr.next();
            	System.out.println(impl.getText());
            	
            }
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}

}
