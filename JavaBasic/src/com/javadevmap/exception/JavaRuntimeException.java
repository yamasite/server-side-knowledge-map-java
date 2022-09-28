package com.javadevmap.exception;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.javadevmap.derive.Tiger;
import com.javadevmap.object.Person;

public class JavaRuntimeException {
	public static void testDivisor() {
		try {
			int i = 6/0;
			System.out.println("i = " + i);
		} catch (Exception e) {
			System.out.println("divisor can not be 0");
		}
	}
	
	public static void testNullPoint() {
		Person person = null;
		System.out.println(person.id);
	}
	
	public static void testArrayOutRange() {
		String[] array = new String[3];
		array[3] = "hello";
	}
	
	public static void testCast() {
		Person person = new Person(1, "xiaoming");
		Object object = person;
		Tiger tiger = (Tiger)object;
	}
	
	public static void testStringToInt() {
		String string = "123abc";
		int i = Integer.valueOf(string);
	}
	
	public static void testArrayRemove() {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		int index = 0;
		for(String string : list) {
			System.out.println(string);
			index++;
			if(index==1) {
				list.remove(index);
			}
		}
	}
	
	public static void main(String[] args) {
		//testDivisor();
		//testNullPoint();
		//testArrayOutRange();
		//testCast();
		//testStringToInt();
		testArrayRemove();
	}
}
