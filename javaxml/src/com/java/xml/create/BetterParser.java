package com.java.xml.create;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BetterParser 
{
	
	public static List<Element> getChildren( Document doc,String tagName )
	{
		Node node = doc.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		return getChildren(element);
	}

	public static List<Element> getChildren( Element element )
	{
		List<Element> childElementList = new LinkedList<Element>();
		NodeList nodesList = element.getChildNodes();
		for( int i = 0 ; i < nodesList.getLength() ; i++ )
	    {
			Node childNode = nodesList.item(i);
	    	if (childNode.getNodeType() == Node.ELEMENT_NODE) 
	    	{
	    		Element childElement = (Element)childNode;
	    		childElementList.add(childElement);
	    	}
	    }
		return childElementList;
	}
	
	public static String[] getElementsNames( List<Element> childList )
	{
		String[] names = null;
		List<String> namesList = new LinkedList<String>();
		try
		{
			for( Element element : childList )
				namesList.add(element.getTagName());
			
			names = new String[namesList.size()];
			namesList.toArray(names);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return names;
	}
	
	public static Document getDocument( String filePath )
	{
		Document doc = null;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			doc = docBuilder.parse(filePath);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document getDocument( InputStream inStream )
	{
		Document doc = null;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			doc = docBuilder.parse(inStream);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return doc;
	}
	

}
