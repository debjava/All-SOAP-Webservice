import java.util.Map;

import javax.wsdl.Definition;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import com.ibm.wsdl.xml.WSDLReaderImpl;



public class TestClient 
{
	private static String webserviceURL = "http://localhost:8080/alldatatypeservice/services/datatypeservice?wsdl";
	private static EndpointReference targetEPR = new EndpointReference( webserviceURL );
	
	public static void main(String[] args) 
	{
		try 
		{
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace("http://service.datatypeservice/xsd", "tns");
			OMElement method = fac.createOMElement("getIntValue", omNs);
			OMElement value11 = fac.createOMElement("intValue", omNs);
			value11.addChild(fac.createOMText(value11, "123"));
			method.addChild(value11);
			
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMElement result = sender.sendReceive(method);
			
			System.out.println(result.getFirstElement().getText());
			
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
