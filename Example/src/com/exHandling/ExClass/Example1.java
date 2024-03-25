package com.exHandling.ExClass;

public class Example1 {
	
	
	public static void main(String[] args) {
		
		
		try {
			
			int a=5/0;
			
		}
		catch (ArithmeticException e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}
	

}
