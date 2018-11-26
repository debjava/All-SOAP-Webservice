import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class TestClient 
{
	public static void main(String[] args) 
	{
		try 
		{
			URL url = new URL("http://localhost:8080/xmloverhttp1/CustomHttpServiceServlet");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream());
			
			out.write("dadasdasdassasa");
			out.close();
			
			BufferedReader in = new BufferedReader(
						new InputStreamReader(
						connection.getInputStream()));
						
			String decodedString;
			StringBuilder sb = new StringBuilder("");

			while ((decodedString = in.readLine()) != null) 
			{
				sb.append(decodedString);
			}
			in.close();
			System.out.println("Message from Server----->"+sb.toString());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}
