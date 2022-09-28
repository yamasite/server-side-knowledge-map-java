package com.javadevmap.templete;

import java.util.ArrayList;
import java.util.List;

class Fruit{
	public void print() {
		System.out.println("It is a Fruit!");
	}
}

class Apple extends Fruit{
	public void print() {
		System.out.println("It is a Apple!");
	}
}

class Orange extends Fruit{
	public void print() {
		System.out.println("It is a Orange!");
	}
}

public class TempleteTypeErase {
	
	public static <T> void print(List<T> list) {
		for(T t:list) {
			if( t instanceof Apple) {
				System.out.println("It is a Apple!");
			}else if( t instanceof Orange ) {
				System.out.println("It is a Orange!");
			}else if( t instanceof Fruit ) {
				System.out.println("It is a Fruit!");
			}
		}
	}
	
	public static void testContainer() {
		List<Fruit> fruits = new ArrayList<Fruit>();
		fruits.add(new Fruit());
		fruits.add(new Apple());
		fruits.add(new Orange());
		for(Fruit fruit : fruits) {
			fruit.print();
		}
		print(fruits);
	}
	
	public static void testExtendWildcard() {
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple());
		//List<Fruit> fruits = apples;
		List<? extends Fruit> fruits2 = apples;
		//fruits2.add(new Apple());
		fruits2.get(0).print();
	}
	
	public static void testSuperWildcard() {
		List<Fruit> fruits = new ArrayList<>();
		fruits.add(new Fruit());
		List<? super Apple> apples = fruits;
		apples.add(new Apple());
		((Fruit)apples.get(0)).print();
	}
	
	public static void main(String[] args) {
		//testContainer();
		//testExtendWildcard();
		testSuperWildcard();
		
	}
}
