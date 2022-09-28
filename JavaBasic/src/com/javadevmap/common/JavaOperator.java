package com.javadevmap.common;

public class JavaOperator {
	
	public static void testArithmeticOperator() {
		int i = 123;
		int j = 5;
		System.out.println("i + j = " + (i+j));
		System.out.println("i - j = " + (i-j));
		System.out.println("i * j = " + (i*j));
		System.out.println("i / j = " + (i/j));
		System.out.println("i % j = " + (i%j));
		System.out.println("i++ = " + (i++));
		System.out.println("i = " + i);
		System.out.println("++i = " + (++i));
		System.out.println("i = " + i);
		System.out.println("i-- = " + (i--));
		System.out.println("i = " + i);
		System.out.println("--i = " + (--i));
		System.out.println("i = " + i);
		
		int sum = i + j;
		System.out.println("sum = " + sum);
		i += j;
		System.out.println("i += j =" + i);
	}
	
	public static void testRelationalOperator() {
		int value = 10;
		System.out.println("value == 10 is " + (value == 10));
		System.out.println("value != 10 is " + (value != 10));
		System.out.println("value != 11 is " + (value != 11));
		System.out.println("value > 9 is " + (value > 9));
		System.out.println("value < 9 is " + (value < 9));
		System.out.println("value >= 10 is " + (value >= 10));
		System.out.println("value <= 8 is " + (value <= 8));
	}
	
	public static void testLogicalOperator() {
		System.out.println("logical true && true is " + (true && true));
		System.out.println("logical true && false is " + (true && false));
		System.out.println("logical true || true is " + (true || true));
		System.out.println("logical true || false is " + (true || false));
		System.out.println("logical false || false is " + (false || false));
		System.out.println("logical !true is " + (!true));
		System.out.println("logical !false is " + (!false));
		
		int i = 10, j = 20;
		System.out.println("logical || short circuit is " + ((i++) > 5 || ((j++) > 2)));
		System.out.println("i = " + i + " j = " + j);
	}
	
	public static void testBitwiseOperator() {
		int i = 15;
		int j = 11;
		System.out.println("i binary is " + Integer.toBinaryString(i));
		System.out.println("j binary is " + Integer.toBinaryString(j));
		System.out.println("i & j = " + (i & j) + ",Binary = " + Integer.toBinaryString(i & j));
		System.out.println("i | j = " + (i | j) + ",Binary = " + Integer.toBinaryString(i | j));
		System.out.println("i ^ j = " + (i ^ j) + ",Binary = " + Integer.toBinaryString(i ^ j));
		System.out.println("~i = " + (~i) + ",Binary = " + Integer.toBinaryString(~i));
		System.out.println("i << 2 = " + (i << 2) + ",Binary = " + Integer.toBinaryString(i << 2));
		System.out.println("i >> 2 = " + (i >> 2) + ",Binary = " + Integer.toBinaryString(i >> 2));
		int k = -1;
		System.out.println("-1 Bianry = " + Integer.toBinaryString(k));
		System.out.println("k << 2 = " + (k << 2) + ",Binary = " + Integer.toBinaryString(k << 2));
		System.out.println("k >> 2 = " + (k >> 2) + ",Binary = " + Integer.toBinaryString(k >> 2));
		System.out.println("k >>> 2 = " + (k >>> 2)  + ",Binary = " + Integer.toBinaryString(k >>> 2));
	}
	
	public static void testConditionOperator() {
		System.out.println("condition operator trueCondition = " + (true?"conditionTrue":"conditionFalse"));
		System.out.println("condition operator falseCondition = " + (false?"conditionTrue":"conditionFalse"));
	}
	
	public static void testStringOperator() {
		String stringValue = "string value ";
		int i = 1,j = 2;
		System.out.println(stringValue + i + j);
		System.out.println(stringValue + (i + j));
		System.out.println(i + j + stringValue);
		stringValue += "add other string ";
		System.out.println(stringValue);
	}
	
	public static void main(String[] args) {
		//testArithmeticOperator();
		//testRelationalOperator();
		//testLogicalOperator();
		//testBitwiseOperator();
		//testConditionOperator();
		testStringOperator();
	}
}
