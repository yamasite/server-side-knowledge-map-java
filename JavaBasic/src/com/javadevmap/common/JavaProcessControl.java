package com.javadevmap.common;

public class JavaProcessControl {
	public enum Color {  
		  RED, GREEN, BLACK, YELLOW  
		} 
	
	public static void testIfElse(int num) {
		System.out.println("num = " + num);
		if(num < 10) {
			System.out.println("num < 10");
		}
		
		if(num < 100) {
			System.out.println("num < 100");
		}else {
			System.out.println("num >= 100");
		}
		
		if(num < 50) {
			System.out.println("num < 50");
		}else if(num>=50 && num <100) {
			System.out.println("num>=50 && num<100");
		}else {
			System.out.println("num > 100");
		}
	}
	
	public static void testSwitch(Color color) {
		switch (color) {
		case RED:
			System.out.println("color is " + Color.RED);
			break;
		case GREEN:
			System.out.println("color is " + Color.GREEN);
			break;
		case BLACK:
			System.out.println("color is " + Color.BLACK);
			break;
		case YELLOW:
			System.out.println("color is " + Color.YELLOW);
			break;
		default:
			break;
		}
	}
	
	public static void testFor() {
		int[] array = new int[10];
		for(int i=0;i<10;i++) {
			array[i] = i;
		}
		
		for(int j:array) {
			System.out.print(j+" ");
		}
	}
	
	public static void testWhile() {
		int[] array = new int[10];
		int i = 0;
		while(i<array.length) {
			array[i] = i;
			i++;
		}
		
		int j = 0;
		do {
			System.out.print(array[j]+" ");
			j++;
		} while (j<array.length);
	}
	
	public static void testBreakAndContinue() {
		int[] array = new int[10];
		for(int i=0;i<10;i++) {
			array[i] = i;
		}
		
		for(int j:array) {
			if(j == 3) {
				continue;
			}
			if(j == 6) {
				break;
			}
			System.out.print(j+" ");
		}
		
	}
	
	public static void testReturn(int num) {
		System.out.println("testReturn start*******");
		if(num == 1) {
			return;
		}else if(num == 2) {
			try {
				System.out.println("testReturn try *******");
				return;
			} finally {
				System.out.println("testReturn finally*******");
			}
		}
		System.out.println("testReturn end*******");
	}
	
	public static void main(String[] args) {
		//testIfElse(51);
		//testSwitch(Color.RED);
		//testFor();
		//testWhile();
		//testBreakAndContinue();
		testReturn(2);
	}
}
