package com.javadevmap.common;

import java.awt.FlowLayout;

import javax.swing.JLabel;

public class JavaBasicTypes {
	static char charval;
	static byte byteval;
	static short shortval;
	static int intval;
	static long longval;
	static float floatval;
	static double doubleval;
	
	public static void basicTypesRange() {

		//char 便于展示范围，所以做了类型转换
		System.out.println("char size = " + Character.SIZE);
		System.out.println("char min = " + (int)Character.MIN_VALUE);
		System.out.println("char max = " + (int)Character.MAX_VALUE);
		System.out.println("char default = " + (int)charval);
		//byte
		System.out.println("byte size = " + Byte.SIZE);
		System.out.println("byte min = " + Byte.MIN_VALUE);
		System.out.println("byte max = " + Byte.MAX_VALUE);
		System.out.println("byte default = " + byteval);
		//short
		System.out.println("short size = " + Short.SIZE);
		System.out.println("short min = " + Short.MIN_VALUE);
		System.out.println("short max = " + Short.MAX_VALUE);
		System.out.println("short default = " + shortval);
		//int
		System.out.println("int size = " + Integer.SIZE);
		System.out.println("int min = " + Integer.MIN_VALUE);
		System.out.println("int max = " + Integer.MAX_VALUE);
		System.out.println("int default = " + intval);
		//long
		System.out.println("long size = " + Long.SIZE);
		System.out.println("long min = " + Long.MIN_VALUE);
		System.out.println("long max = " + Long.MAX_VALUE);
		System.out.println("long default = " + longval);
		//float
		System.out.println("float size = " + Float.SIZE);
		System.out.println("float min = " + Float.MIN_VALUE);
		System.out.println("float max = " + Float.MAX_VALUE);
		System.out.println("float default = " + floatval);
		//double
		System.out.println("double size = " + Double.SIZE);
		System.out.println("double min = " + Double.MIN_VALUE);
		System.out.println("double max = " + Double.MAX_VALUE);
		System.out.println("double default = " + doubleval);
	}
	
	public static void testConversion() {
		int i = Integer.MAX_VALUE;
		System.out.println("max int = " + i);
		short j = (short)i;
		System.out.println("max int to short = " + j);
		long k = i;
		System.out.println("max int to long = " + k);
		float x = i;
		System.out.println("max int to float = " + x);
		double y = i;
		System.out.println("max int to double + " + y);
	}
	
	public static void testOutRange() {
		int i = Integer.MAX_VALUE;
		System.out.println("max int = " + i);
		i = i + 1;
		System.out.println("max int + 1 = " + i);
		
		int j = Integer.MIN_VALUE;
		System.out.println("min int = " + j);
		j = j - 1;
		System.out.println("min int - 1 = " + j);
		
		double x = Double.MAX_VALUE;
		System.out.println("max double = " + x);
		x = x + Double.MAX_VALUE;
		System.out.println("max double + max double = " + x);
		
		double y = -Double.MAX_VALUE;
		System.out.println("- max double = " + y);
		y = y - Double.MAX_VALUE;
		System.out.println("- max double - max double = " + y);
	}
	
	public static void testBoolean() {
		boolean bool = false;
		int i = 1;
		//bool = (boolean)i;
		bool = (i>=1)?true:false;
		System.out.println("boolean value = " + bool);
	}
	
	public static void testOperation() {
		int i = 6,j = 5;
		int k = i/j;
		System.out.println("int i/j = " + k);
		double x = i/j;
		System.out.println("double i/j = " + x);
		double y = (double)i/j;
		System.out.println("double (double)i/j = " + y);
		double z = i*1.0/j;
		System.out.println("double i*1.0/j = " + z);
	}
	
	public static void main(String[] args) {
		//basicTypesRange();
		//testConversion();
		//testOutRange();
		//testBoolean();
		testOperation();
	}
}
