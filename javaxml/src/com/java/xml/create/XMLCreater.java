package com.java.xml.create;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLCreater 
{
	public static void createXML()
	{
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document document = documentBuilder.newDocument();
	        //Creating root element
			Element rootElement = document.createElement("Emp");
			document.appendChild(rootElement);
			//Name element
			Element nameElement = document.createElement("Name");
			Text nameText = document.createTextNode("Deba");
			nameElement.appendChild(nameText);
			rootElement.appendChild(nameElement);
			//Address element
			Comment comment = document.createComment("It is a Comment");
			document.appendChild(comment);
			Element adrsElement = document.createElement("Address");
			adrsElement.setAttribute("Present", "Bangalore");
			Text adrsText = document.createTextNode("New Address");
			adrsElement.appendChild(adrsText);
			
			
			rootElement.appendChild(adrsElement);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        String xmlPath = "test.xml";
	        OutputStream os = new FileOutputStream( xmlPath );
	        StreamResult result =  new StreamResult(os);
	        System.out.println(result.toString());
	        transformer.transform(source, result);
			
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		createXML();
	}

}
