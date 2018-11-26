package com.ddlab.webservice.server;

public class Primitive 
{
	public int[] getData( int[] datas )
	{
		System.out.println("*************** Data received at Server side *****************");
		for( int i = 0 ; i < datas.length ; i++ )
			System.out.println(datas[i]);
		System.out.println("*************** Data received at Server side *****************");
		return datas;
	}
}
