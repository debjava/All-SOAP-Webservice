package com.ddlab.webservice.server;


public class ZodiacUtil
{
	public String[] getZodiacSignList()
	{
		String[] signsList = new String[] { "Aries", "Taurus", "Gemini",
				"Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius",
				"Capricorn", "Aquarius", "Pisces" };
		return signsList;
	}
	public String getZodiacSign( int day ,int month , int year)
	{
		String zodiacSign = null;
		if (month == 1 && day >=20 || month == 2 && day <=18)
			zodiacSign = "Aquarius";
		else if (month == 1 && day > 31)
			zodiacSign = "No zodiac sign found";
		else if (month == 2 && day >=19 || month == 3 && day <=20) 
			zodiacSign = "Pisces";
		else if (month == 2 && day > 29) 
			zodiacSign = "No zodiac sign found";
		else if (month == 3 && day >=21 || month == 4 && day <=19) 
			zodiacSign = "Aries";
		else if (month == 3 && day > 31)
			zodiacSign = "No zodiac sign found";
		else if (month == 4 && day >=20 || month == 5 && day <=20)
			zodiacSign = "Taurus";
		else if (month == 4 && day > 30) 
			zodiacSign = "No zodiac sign found";
		else if (month == 5 && day >=21 || month == 6 && day <=21)
			zodiacSign = "Gemini";
		else if (month == 5 && day > 31)
			zodiacSign = "No zodiac sign found";
		else if (month == 6 && day >=22 || month == 7 && day <=22)
			zodiacSign = "Cancer";
		else if (month == 6 && day > 30) 
			zodiacSign = "No zodiac sign found";
		else if (month == 7 && day >=23 || month == 8 && day <=22) 
			zodiacSign = "Leo";
		else if (month == 7 && day > 31) 
			zodiacSign = "No zodiac sign found";
		else if (month == 8 && day >=23 || month == 9 && day <=22) 
			zodiacSign = "Virgo";
		else if (month == 8 && day > 31) 
			zodiacSign = "No zodiac sign found";
		else if (month == 9 && day >=23 || month == 10 && day <=22)
			zodiacSign = "Libra";
		else if (month == 9 && day > 30) 
			zodiacSign = "No zodiac sign found";
		else if (month == 10 && day >=23 || month == 11 && day <=21)
			zodiacSign = "Scorpio";
		else if (month == 10 && day > 31) 
			zodiacSign = "No zodiac sign found";
		else if (month == 11 && day >=22 || month == 12 && day <=21) 
			zodiacSign = "Sagittarius";
		else if (month == 11 && day > 30) 
			zodiacSign = "No zodiac sign found";
		else if (month == 12 && day >=22 || month == 1 && day <=19) 
			zodiacSign = "Capricorn";
		else if (month == 12 && day > 31) 
			zodiacSign = "No zodiac sign found";
		else
			zodiacSign = "No zodiac sign found";
		return zodiacSign;
	}
}
