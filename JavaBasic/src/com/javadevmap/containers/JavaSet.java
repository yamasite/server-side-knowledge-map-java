package com.javadevmap.containers;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.javadevmap.object.Person;

public class JavaSet {
	public static void testSet() {
		Set set = new HashSet<>();
		set.add(new Person(1, "lilei"));
		set.add(new Person(2, "hanmeimei"));
		set.add(new Person(3, "lucy"));
		set.add(new Person(3, "lucy"));
		System.out.println(set);
		set.remove(new Person(2, "hanmeimei"));
		System.out.println(set);
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			if(object instanceof Person) {
				System.out.println("it is a person and " + object);
			}
		}
	}
	
	public static void testTreeSet() {
		TreeSet<Person> set = new TreeSet<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if(o1.id < o2.id) {
					return -1;
				}else if(o1.id == o2.id) {
					return 0;
				}else {
					return 1;
				}
			}
		});
		set.add(new Person(40, "xiaoming"));
		set.add(new Person(29, "xiaoming"));
		set.add(new Person(41, "xiaoming"));
		set.add(new Person(32, "xiaoming"));
		set.add(new Person(50, "xiaoming"));
		set.add(new Person(37, "xiaoming"));
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		//testSet();
		testTreeSet();
	}
}
