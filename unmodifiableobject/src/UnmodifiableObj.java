
public class UnmodifiableObj extends Bean
{
	private final Bean bean ;
	public UnmodifiableObj( Bean bean )
	{
		super();
		this.bean = bean;
	}
	
	
	public void setAge(int age) 
	{
		throw new UnmodifiableException("Value can not be modified");
	}
	
	public void setName(String name) 
	{
		throw new UnmodifiableException("Value can not be modified");
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
