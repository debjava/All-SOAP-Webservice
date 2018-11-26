import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

//Using cursor api
public class StaxCursorParser 
{
	private XMLInputFactory inputFactory = null;    
    private XMLStreamReader xmlReader = null;
    
    public StaxCursorParser() throws FileNotFoundException, XMLStreamException 
    {
        inputFactory = XMLInputFactory.newInstance();
        xmlReader = inputFactory.createXMLStreamReader(
                new FileReader("config/emp.xml"));
    }
    
    public void read() throws Exception{
           
//        xmlReader = inputFactory.createXMLStreamReader(
//            new FileReader("config/emp.xml"));
        
        while (xmlReader.hasNext())
        {
            Integer eventType = xmlReader.next();
            if (eventType.equals(XMLEvent.START_ELEMENT))
            {
                System.out.print(" " + xmlReader.getName() + " ");
            }
            else if (eventType.equals(XMLEvent.CHARACTERS))
            {
                System.out.print(" " + xmlReader.getText() + " ");
            }
            else if (eventType.equals(XMLEvent.ATTRIBUTE))
            {
                System.out.print(" " + xmlReader.getName() + " ::: "+xmlReader.getText());
            }
            else if (eventType.equals(XMLEvent.END_ELEMENT))
            {
                System.out.print(" " + xmlReader.getName() + " ");
            }
        }        
        xmlReader.close();
    }

	public static void main(String[] args) 
	{
		try
		{
            new StaxCursorParser().read();            
        }
		catch(Exception exception)
		{
            exception.printStackTrace();
        }

	}

}
