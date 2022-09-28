package com.javadevmap.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JavaList {
	public static void testArrayList() {
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		System.out.println(list);
		System.out.println(list.get(3));
		list.remove("four");
		System.out.println(list);
		System.out.println("list contains one is " + list.contains("one"));
		list.add("five");
		list.set(3, "four");
		System.out.println(list);
		System.out.println("list index of two is " + list.indexOf("two"));
		System.out.println("sub list is " + list.subList(1, 3));
		if(!list.isEmpty()) {
			Object[] strings = list.toArray();
			System.out.println("array length is " + strings.length);
		}
	}
	
	public static void testLinkedList() {
		String[] strings = {"one","two","three","four","five","six","seven","eight","nine","ten"};
		List<String> list = new LinkedList<String>();
		list.addAll(Arrays.asList(strings));
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String string = it.next();
			if(string=="three" || string=="six" || string=="nine") {
				it.remove();
			}
		}
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		//testArrayList();
		testLinkedList();
	}
}
