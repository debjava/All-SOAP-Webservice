import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XMLCreater 
{
	public static void main(String[] args) 
	{
		try 
		{
			String root = "Employees";
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement(root);
	        document.appendChild(rootElement);
	        for( int i = 0 ; i < 5 ; i++ )
	        {
	        	String element = "Emp";
	        	Element em = document.createElement(element);
	        	String data = "name "+i;
				em.appendChild(document.createTextNode(data));
				em.setAttribute("id", "A00"+i);
				rootElement.appendChild(em);
	        }
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        
	        DOMSource source = new DOMSource(document);
	        StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            
            transformer.transform(source, result);
            String xmlString = sw.toString();
            
            System.out.println("XML String \n"+xmlString);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
