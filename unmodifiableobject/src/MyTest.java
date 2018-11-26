
public class MyTest 
{
	public static void main(String[] args) 
	{
		Bean b = new Bean();
		b.setAge(23);
		b.setName("sss");
		
		try
		{
			Bean bb = new UnmodifiableObj(b).getUnModifiableBean();
			System.out.println(bb.getName());
			System.out.println(bb.getAge());
			
//			bb.setAge(34);
			bb.setName("abcd");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
