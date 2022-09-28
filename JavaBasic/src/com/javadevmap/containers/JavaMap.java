package com.javadevmap.containers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.javadevmap.object.Person;

public class JavaMap {
	public static void testHashMap() {
		HashMap<Person, String> map = new HashMap<>();
		map.put(new Person(1,"xiaoming"), "Musician");
		map.put(new Person(1,"xiaoming"), "Musician");
		map.put(new Person(2,"daming"), "Scientist");
		map.put(new Person(3,"xiaobai"), "Astronaut");
		System.out.println(map);
		
		for(Map.Entry<Person, String> entry : map.entrySet()) {
			System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
		}
		
		for(Person person : map.keySet()) {
			System.out.println(person);
		}
		
		for(String string : map.values()) {
			System.out.println(string);
		}
		
		Iterator<Map.Entry<Person, String>> its = map.entrySet().iterator();
		while (its.hasNext()) {
			Map.Entry<Person, String> entry = its.next();
			System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		testHashMap();
	}
}
