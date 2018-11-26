package com.test;

import java.io.IOException;

public class B extends A 
{
	public void show( String s ) 
	{
		System.out.println("In class B");
	}
	
	public static void main(String[] args) 
	{
		A a1 = new B();
		a1.show(null);
		
		
	}
}


