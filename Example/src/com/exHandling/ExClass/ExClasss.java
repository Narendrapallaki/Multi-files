package com.exHandling.ExClass;

public class ExClasss {

	
	
	
	static void voteValidation(int age) throws ARRException 
	{
		if (age<18) {
			
			
			throw new ARRException("Age is lessthan 18");
		}
		else
		{
			System.out.println("You are eligible for vote "+age);
		}
	}
	public static void main(String[] args)
	{

		
		try {
			ExClasss.voteValidation(12);
		} catch (ARRException e) {
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
