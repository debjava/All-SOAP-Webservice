import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


public class Parser1 
{
	public static void main(String[] args) 
	{
		String filePath = "config/emp.xml";

		try 
		{
			InputStream in = new FileInputStream(filePath);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader parser = factory.createXMLStreamReader(in);
			for (int event = parser.next();  
			event != XMLStreamConstants.END_DOCUMENT;
			event = parser.next()) 
			{
				switch (event) 
				{
				  case XMLStreamConstants.START_DOCUMENT:
				    System.out.println("Start document " + parser.getLocalName());
				    break;
				  case XMLStreamConstants.START_ELEMENT:
				    System.out.println("Start element " + parser.getLocalName());
				 	System.out.println("Element text " + parser.getElementText());
				    break;
				  case XMLStreamConstants.END_ELEMENT:
				    System.out.println("End element " + parser.getLocalName());
				    break;
				  default:
				    break;
				  }

				
				
				
				
//				System.out.println(parser.getLocalName());
//				System.out.println(parser.getElementText());
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
