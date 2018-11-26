import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class ReadingUsingEventIteratorApi {
    
    private XMLInputFactory inputFactory = null;    
    private XMLEventReader xmlEventReader = null;
    
    public ReadingUsingEventIteratorApi() throws FileNotFoundException, XMLStreamException {
        inputFactory = XMLInputFactory.newInstance();     
//        xmlEventReader = inputFactory.createXMLEventReader(
//                new FileReader("config/emp.xml"));
        
        xmlEventReader = inputFactory.createXMLEventReader(
                new FileReader("config/calender.xml"));
    }
    
    public void read() throws Exception{
           
        
        
        while (xmlEventReader.hasNext()){
            
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
//            System.out.println("Event Type ::: "+xmlEvent.getEventType());
            if (xmlEvent.isStartElement()){
                System.out.print(" " + xmlEvent.asStartElement().getName() + " ");
            }else if (xmlEvent.isCharacters()){
                System.out.print(" " + xmlEvent.asCharacters().getData() + " ");
            }else if (xmlEvent.isEndElement()){
                System.out.print(" " + xmlEvent.asEndElement().getName() + " ");
            }
            else if(xmlEvent.isAttribute())
            {
            	System.out.print(" " + xmlEvent.asCharacters().getData() + " ");
            }
        }
        
        xmlEventReader.close();
    }

    public static void main(String args[]){
        try{
            ReadingUsingEventIteratorApi obj = new ReadingUsingEventIteratorApi();
            obj.read();            
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }    
}
