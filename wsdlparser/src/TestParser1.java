
public class TestParser1 
{
	public static void main(String[] args) throws Exception
	{
		String url = "http://localhost:8080/empservice/services/empservice?wsdl";
		
		new wsdlparser1().parseWSDL(url);
		
		
	}
}
