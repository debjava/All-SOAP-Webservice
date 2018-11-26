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


public class TestClient1 
{
	public static OMElement getPricePayload(String symbol) 
	{
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                                                 "http://service.book/xsd", "ns2");
        
        OMNamespace omNs1 = fac.createOMNamespace(
                "http://service.book/xsd", "ns1");

        OMElement book = fac.createOMElement("Book", omNs);
        
        OMElement value1 = fac.createOMElement("isbnNo", omNs);
        value1.addChild(fac.createOMText(value1, "27"));
        book.addChild(value1);
        
        OMElement value2 = fac.createOMElement("name", omNs);
        value2.addChild(fac.createOMText(value1, "asdfdddddddddd"));
        book.addChild(value2);
        
        OMElement value3 = fac.createOMElement("price", omNs);
        value3.addChild(fac.createOMText(value3, "1978"));
        book.addChild(value3);
        
        OMElement method = fac.createOMElement("getYesNo", omNs1);
        
        method.addChild(book);
        return method;
    }
	
	
	public static void main(String[] args)
	{
		
//		EndpointReference targetEPR = 
//	        new EndpointReference(
//	                              "http://localhost:8080/bookservice/services/bookservice");
		
		EndpointReference targetEPR = 
	        new EndpointReference(
	                              "http://localhost:7001/bookservice/services/bookservice");
		
		try
		{
			OMElement getPricePayload = getPricePayload("WSO");
			Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);

            OMElement result = sender.sendReceive(getPricePayload);

            
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
