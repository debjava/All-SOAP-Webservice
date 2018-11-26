package com.ddlabs.rnd.webservice.server;

public class DataRetriever 
{

	public String[] getData()
	{
		System.out.println("Web service method getData() called");
		String[] datas = new String[]{"a","b","c","d"};
		return datas;
	}

}
