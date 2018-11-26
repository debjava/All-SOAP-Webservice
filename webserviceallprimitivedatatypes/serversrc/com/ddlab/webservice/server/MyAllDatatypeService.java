package com.ddlab.webservice.server;

public class MyAllDatatypeService 
{
	public int getIntValue( int intValue )
	{
		System.out.println("Value received at server side----->"+intValue);
		return intValue;
	}
	
	public float getFloatValue( float floatValue )
	{
		System.out.println("Value received at server side----->"+floatValue);
		return floatValue;
	}
	
	public double getDoubleValue( double doubleValue )
	{
		System.out.println("Value received at server side----->"+doubleValue);
		return doubleValue;
	}
	
	public boolean getBooleanValue( boolean booleanValue )
	{
		System.out.println("Value received at server side----->"+booleanValue);
		return booleanValue;
	}
	
	public byte getByteValue( byte byteValue )
	{
		System.out.println("Value received at server side----->"+byteValue);
		return byteValue;
	}
	
	public String getStringValue( String stringValue )
	{
		System.out.println("Value received at server side----->"+stringValue);
		return stringValue;
	}
	
	
}
