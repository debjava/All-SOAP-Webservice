import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class TestDOB 
{
	public static Date getDate(String dateString)
	{
		Date date = null;
		String dateFormatString = "dd-MM-yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatString);
		try
		{
			date = sdf.parse(dateString);
			
//			ArrayList al = new ArrayList();
//			Collections.unmodifiableList(list)
			
			
		}
		
		
//		catch( FileNotFoundException fnfe )
//		{
//			
//		}
		catch( NullPointerException npe )
		{
			npe.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}
	
	public static void showDOB( String dateStr )
	{
		Date fromDate = getDate(dateStr);
		Date todays = new Date();
		long todaysTime = todays.getTime();
		long dobTime = fromDate.getTime();
		long difference = todaysTime - dobTime;
		System.out.println("Total Differnece------->"+difference);
		
//		long days = difference / 24;
//		int month = days / 12 ;
//		int year = difference / 365;
//		
//		
//		
//		int date =  todays.getDate() - fromDate.getDate();
//		int month = todays.getMonth() - fromDate.getMonth();
//		int year =  todays.getYear() - fromDate.getYear();
//		System.out.println(year+" Years "+month+" months");
		
	}
	
	public static void main(String[] args) 
	{
		String fromDateStr = "27-07-1975";
		showDOB(fromDateStr);
//		String dateFormatString = "dd-MM-yyyy";
//		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatString);
//		try
//		{
//			Date date = sdf.parse(fromDateStr);
//			System.out.println("Date---->"+date);
//			String todaysDateStr = new Date().toString();
//			System.out.println("Todays Date--------->"+todaysDateStr);
//			
//			
//			
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
		
	}
	
}
