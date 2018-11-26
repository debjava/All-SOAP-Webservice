package com.ddlab.webservice.server;

public class Laptop 
{
	private String name;
	private String osName;
	private int ramSize;
	private int hardDriveSize;
	private float priceRange;
	private boolean bag;
	
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public int getRamSize() {
		return ramSize;
	}
	public void setRamSize(int ramSize) {
		this.ramSize = ramSize;
	}
	public int getHardDriveSize() {
		return hardDriveSize;
	}
	public void setHardDriveSize(int hardDriveSize) {
		this.hardDriveSize = hardDriveSize;
	}
	public float getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(float priceRange) {
		this.priceRange = priceRange;
	}
	public boolean isBag() {
		return bag;
	}
	public void setBag(boolean bag) {
		this.bag = bag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	


}
