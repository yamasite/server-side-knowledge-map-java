package com.javadevmap.exception;

public class CustomExceptionDemo {
	public static void testRuntimeExceptino(){
		throw new CustomRuntimeException();
	}
	
	public static void testException() throws CustomException{
		throw new CustomException();
	}
	
	public static void main(String[] args) {
		try {
			testException();
		} catch (CustomException e) {
			System.out.println("catch CustomException");
		} catch (Exception e) {
			System.out.println("catch Exception");
		}
	}
}
