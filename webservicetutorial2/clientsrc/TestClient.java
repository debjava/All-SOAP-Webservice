import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.OperationClient;

import com.ddlab.webservice.client.ZodiacStub;


public class TestClient 
{
	
	public static SOAPEnvelope getSoapEvelope( OMElement omElement )
	{
		SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
        SOAPEnvelope envelope = fac.getDefaultEnvelope();
        OMNamespace omNs =   fac.createOMNamespace(
            "http://ws.apache.org/axis2/xsd", "ns1");
        envelope.getBody().addChild(omElement);
        return envelope;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		String endPoint = "http://localhost:8080/astroservice/services/Zodiac?wsdl";
		try 
		{
			ZodiacStub stub = new ZodiacStub( endPoint );
			ZodiacStub.GetZodiacSign zodiacSign = new ZodiacStub.GetZodiacSign();
			zodiacSign.setDay(27);
			zodiacSign.setMonth(03);
			zodiacSign.setYear(1977);
			ZodiacStub.GetZodiacSignResponse response = stub.getZodiacSign(zodiacSign);
			System.out.println("Your Zodiac Sign ::: "+response.get_return());
			
			
//			System.out.println(getSoapEvelope(zodiacSign.getOMElement(new QName("getZodiacSign"), null)));
//			stub._getServiceClient().g
			
			ZodiacStub.GetZodiacSignListResponse responseList = stub.getZodiacSignList();
			
			System.out.println(getSoapEvelope(responseList.getOMElement(new QName("getZodiacSign"), null)));
			
			System.out.println();
			
			String[] s = responseList.get_return();
			for( String ss : s )
				System.out.println(ss);
			
		}
		catch (AxisFault e) 
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
}
