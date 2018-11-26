package com.ddlab.webservice.server;

public class SystemService 
{
	public Laptop[] getLapTops( Specification spec )
	{
		System.out.println("********** Data at server side *************");
		System.out.println("Specification Name ::: "+spec.getName());
		System.out.println("Operating System Name ::: "+spec.getOsName());
		System.out.println("Price Range ::: "+spec.getPriceRange());
		System.out.println("RAM Size ::: "+spec.getRamSize());
		System.out.println("********** Data at server side *************");
		
		Laptop[] ls = new Laptop[3];
		
		ls[0] = new Laptop();
		ls[0].setBag(true);
		ls[0].setHardDriveSize(320);
		ls[0].setOsName("Windows 7");
		ls[0].setPriceRange(32345.67f);
		ls[0].setRamSize(3);
		ls[0].setName("Lenovo");
		
		ls[1] = new Laptop();
		ls[1].setBag(false);
		ls[1].setHardDriveSize(520);
		ls[1].setOsName("Windows Vista");
		ls[1].setPriceRange(12345.67f);
		ls[1].setRamSize(4);
		ls[1].setName("Dell");
		
		ls[2] = new Laptop();
		ls[2].setBag(true);
		ls[2].setHardDriveSize(200);
		ls[2].setOsName("Redhat Linux");
		ls[2].setPriceRange(33345.67f);
		ls[2].setRamSize(2);
		ls[2].setName("HP Pavlion");
		
		
		return ls;
	}
}
