package com.java.xml.create;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLTraveller 
{
	public static void parseDocument( Document doc )
	{
		Node node = ( Node )doc;
		System.out.println(node.getNodeName());
		switch( node.getNodeType() )
		{
		case Node.ATTRIBUTE_NODE:
			Attr attr = (Attr)node;
			showAttributeNode( attr );
		}
	}
	private static void showAttributeNode( Attr attr )
	{

	}

	public static void main(String[] args) 
	{
		String fileName = "config/Details.xml";
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(fileName);
			parseDocument(doc);
		}
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		catch (SAXException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
