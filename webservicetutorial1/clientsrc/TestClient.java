import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis.client.Stub;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.OperationClient;

import com.ddlab.webservice.client.DataRetrieverStub;
import com.ddlab.webservice.client.DataRetrieverStub.GetDataResponse;

public class TestClient 
{
	public static class A extends DataRetrieverStub
	{

		public A( String endPoint ) throws AxisFault 
		{
			super(endPoint);
		}

		public static SOAPFactory getFactory( String s )
		{
			return getFactory(s);
		}

	}

	public static class B extends Stub
	{

	}


	public static void main(String[] args) 
	{
		String endPoint = "http://localhost:8080/myservice/services/DataRetriever?wsdl";
		try 
		{
			A astub = new A(endPoint);
			GetDataResponse response = astub.getData();
			String[] datas = response.get_return();


			OperationClient _operationClient = astub._getServiceClient().createClient( new QName("getData"));
			System.out.println(_operationClient.getOptions().getSoapVersionURI());
			
//			SOAPFactory factory = astub.getFactory(_operationClient.getOptions().getSoapVersionURI());
//			SOAPEnvelope env = factory.getDefaultEnvelope();
//			System.out.println(env);



//			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
//					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
//			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
//			System.out.println(_returnEnv);


			for( String s : datas )
				System.out.println(s);



			//			DataRetrieverStub stub = new DataRetrieverStub( endPoint );
			////			stub._getServiceClient().getOptions().get
			////			stub._getServiceClient().getAxisService().get
			//			GetDataResponse response = stub.getData();
			//			String[] datas = response.get_return();
			//			for( String s : datas )
			//				System.out.println(s);
			//			
			//			OperationClient _operationClient = stub._getServiceClient().createClient( new QName("getData"));
			//			Stub s = new Stub();
			////			SOAPFactory s = new SOAPFactory(_operationClient);
			////			OperationClient _operationClient = stub._getServiceClient().createClient(new QName("getData"));
			////			DataRetrieverStub.ExtensionMapper. em = new DataRetrieverStub.ExtensionMapper();
			////			em.



		}
		catch (AxisFault e) 
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
}
