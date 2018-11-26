package com.ddlab.webservice.server;

public class EmplyeeInfoService 
{
	public Employee getEmployeeInfo( Employee emp )
	{
		System.out.println("Data received at server side");
		
		System.out.println("Emp Name : "+emp.getName());
		System.out.println("Emp Id : "+emp.getId());
		System.out.println("Emp Salary :"+emp.getSalary());
		
		System.out.println("Data received at server side");
		
		Employee em = new Employee();
		
		em.setId(1234);
		em.setName("Deba");
		em.setSalary(13.78f);
		
		return em;
	}
}
