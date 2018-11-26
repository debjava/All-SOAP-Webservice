import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class AxiomParser1 
{

	public static void main(String[] args)
	{
		try {
            //create the parser
            XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("config/calender.xml"));
            StAXOMBuilder builder = new StAXOMBuilder(parser);
            //get the root element
            OMElement documentElement = builder.getDocumentElement();

            //dump the out put to console with caching
//            System.out.println(documentElement.toStringWithConsume()); 
            
            
            Iterator itr = documentElement.getChildElements();
            while( itr.hasNext() )
    		{
    			OMElement result11 = (OMElement) itr.next();
//    			System.out.println(result11);
    			
    			Iterator itr1 = result11.getChildElements();
    			while(itr1.hasNext())
    			{
    				OMElement res = (OMElement) itr1.next();
    				System.out.println(res.getLocalName()+":::"+res.getText());
    			}
    			
    			
    		}	
            

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

	}

}
