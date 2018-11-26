package com.ddlab.webservice;

public class BookService 
{
	public Book getBookDetails()
	{
		Book b = new Book();
		b.setIsbnNo(123);
		b.setName("Deba");
		b.setPrice(25.45);
		
		return b;
	}
	
	public String getYesNo( Book b )
	{
		System.out.println(b.getIsbnNo());
		System.out.println(b.getName());
		System.out.println(b.getPrice());
		return "Yes";
	}
}
