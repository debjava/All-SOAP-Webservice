package com.java.xml.create;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class EmpCreator 
{
	public static void main(String[] args) 
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			//Root Element
			Element rootElement = doc.createElement("Employees");
			doc.appendChild(rootElement);
			
			for( int i = 0 ; i < 5 ; i++ )
			{
				Element empElement = doc.createElement("Employee");
				//Setting the attribute
				empElement.setAttribute("Id", new Integer(i).toString());
				empElement.setAttribute("Location", "Blr");
				
				Comment comment = doc.createComment("A normal comment");
				empElement.appendChild(comment);
				
				Element nameElement = doc.createElement("Name");
				Text nameText = doc.createTextNode("Name "+i);
				nameElement.appendChild(nameText);
				empElement.appendChild(nameElement);
				
				Element ageElement = doc.createElement("Age");
				Text ageText = doc.createTextNode( new Integer(i).toString());
				ageElement.appendChild(ageText);
				empElement.appendChild(ageElement);
				
				rootElement.appendChild(empElement);
			}
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource domSrc = new DOMSource( doc );
			String xmlPath = "config/check.xml";
			OutputStream out = new FileOutputStream( xmlPath );
			StreamResult result = new StreamResult(out);
			transformer.transform(domSrc, result);
			
		}
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} catch (TransformerConfigurationException e) 
		{
			e.printStackTrace();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (TransformerException e) 
		{
			e.printStackTrace();
		}
	}
}
