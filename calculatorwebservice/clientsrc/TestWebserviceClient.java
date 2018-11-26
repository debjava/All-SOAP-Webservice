import com.ddlab.webservice.client.WebcalculatorStub;

public class TestWebserviceClient 
{
	public static void main(String[] args) 
	{
		String endPoint = "http://localhost:8080/webcalculator/services/webcalculator?wsdl";
		try
		{
			WebcalculatorStub stub = new WebcalculatorStub(endPoint);
			WebcalculatorStub.Add add = new WebcalculatorStub.Add();
			add.setFirstNo(10);
			add.setSecondNo(20);
			WebcalculatorStub.AddResponse res = stub.add(add);
			System.out.println(res.get_return());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
