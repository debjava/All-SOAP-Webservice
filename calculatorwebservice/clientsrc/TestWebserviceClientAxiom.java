import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class TestWebserviceClientAxiom 
{
//	private static EndpointReference targetEPR = 
//        new EndpointReference(
//                              "http://localhost:8080/webcalculator/services/webcalculator");
	
	
	private static EndpointReference targetEPR = 
        new EndpointReference(
                              "http://123.237.216.131:7001/webcalculator/services/webcalculator");
	
	
	
	public static OMElement getPricePayload(String no1,String no2)
	{
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                                                 "http://service.webcalculator/xsd", "tns");

        OMElement method = fac.createOMElement("add", omNs);
        
        OMElement value1 = fac.createOMElement("firstNo", omNs);
        value1.addChild(fac.createOMText(value1, no1));
        method.addChild(value1);
        
        OMElement value2 = fac.createOMElement("secondNo", omNs);
        value2.addChild(fac.createOMText(value2, no2));
        method.addChild(value2);
        
        
        return method;
    }
	
	public static void main(String[] args) throws AxisFault 
	{
		OMElement getPricePayload = getPricePayload("12","13");
		
		Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);
        
        OMElement result = sender.sendReceive(getPricePayload);

        String response = result.getFirstElement().getText();
        System.err.println("Addition of two numbers : " + response);
        
	}
}
