package com.javadevmap.containers;

import java.util.Arrays;

public class JavaArray {
	
	public static void testDirectConArray() {
		String[] strings = {"lilei","hanmeimei","lucy"};
		System.out.println("array length = " + strings.length);
		System.out.println(Arrays.asList(strings));
	}
	
	public static void testNewConArray() {
		String[] strings = new String[5];
		strings[0] = "A";
		strings[1] = "B";
		strings[2] = "C";
		strings[3] = "D";
		System.out.println("array length = " + strings.length);
		System.out.println(Arrays.asList(strings));
	}
	
	public static void testTdimArray() {
		String[][] strings = {{"one","two","three"},{"four","five","six"},{"seven","eight","nine"}};
		for(String[] tempStrings:strings) {
			System.out.println(Arrays.asList(tempStrings));
		}
	}
	
	public static void testNewTidmArray() {
		String[][] strings = new String[2][];
		strings[0] = new String[2];
		strings[1] = new String[4];
		strings[0][0] = "up";
		strings[0][1] = "down";
		strings[1][0] = "east";
		strings[1][1] = "south";
		strings[1][2] = "west";
		strings[1][3] = "north";
		for(String[] tempStrings:strings) {
			System.out.println(Arrays.asList(tempStrings));
		}
	}
	
	public static void main(String[] args) {
//		testDirectConArray();
//		testNewConArray();
		testTdimArray();
		testNewTidmArray();
	}
}
