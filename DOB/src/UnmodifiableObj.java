
public class UnmodifiableObj extends Bean
{
	private final Bean bean ;
	public UnmodifiableObj( Bean bean )
	{
		super();
		this.bean = bean;
	}
	
//	public boolean equals(Object o) {return (Bean)o == this || bean.equals(o);}
//	public int hashCode() 		{return bean.hashCode();}
	
	public void setAge(int age) 
	{
//		System.out.println("It is called");
		throw new NullPointerException("Can not be modified");
	}
	
	public void setName(String name) 
	{
		throw new NullPointerException("Can not be modified");
	}
	
	public Bean getUnModifiableBean()
	{
		return this;
	}
	
	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return bean.getAge();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return bean.getName();
	}
}
