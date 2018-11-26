import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpService extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		
		System.out.println("Length of the received String----->"+request.getContentLength());
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
		String msg = null;
		StringBuilder sb = new StringBuilder("");
		while( (msg = reader.readLine()) != null )
		{
			sb.append(msg);
		}
		System.out.println("Final Received Message---->"+sb.toString());
		
		//Form the output and send to client
		
		response.getOutputStream().print("This is the response generated from the server");

	}

}
