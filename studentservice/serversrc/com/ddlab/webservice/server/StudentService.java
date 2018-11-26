package com.ddlab.webservice.server;

public class StudentService 
{
	public ExamResult[] getExamResult( Student[] stud )
	{
		System.out.println("**************** Data Received at Server Side *****************");
		for( int i = 0 ; i < stud.length ; i++ )
		{
			System.out.println("Name of the Student------>"+stud[i].getName());
			System.out.println("Roll No of the Student------>"+stud[i].getRollNo());
		}
		System.out.println("**************** Data Received at Server Side *****************");
		ExamResult[] er = new ExamResult[3];
		
		er[0] = new ExamResult();
		er[0].setName("John Britto");
		er[0].setRollNo(23);
		er[0].setResult("Passed");
		
		er[1] = new ExamResult();
		er[1].setName("Ketty");
		er[1].setRollNo(27);
		er[1].setResult("Passed");
		
		er[2] = new ExamResult();
		er[2].setName("Arnold");
		er[2].setRollNo(29);
		er[2].setResult("Passed");
		
		return er;
	}
}
