package com.javadevmap.derive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tiger extends Animal implements Comparable<Tiger>{
	public String roar = "ao";
	
	public Tiger(int weight,String roar) {
		super(weight);
		this.roar = roar;
	}

	@Override
	public void move() {
		System.out.println("tiger can run!");
	}
	
	@Override
	public String toString() {
		return "the tiger weight is " + weight;
	}

	@Override
	public int compareTo(Tiger o) {
		if(this.weight < o.weight) {
			return -1;
		}else if(this.weight == o.weight) {
			return 0;
		}else {
			return 1;
		}
	}

	public static void main(String[] args) {
//		Tiger tiger = new Tiger(500, "ao!");
//		tiger.move();
//		System.out.println("tiger weight = " + tiger.weight + " tiger roar = " + tiger.roar);
//		
//		Animal animal = new Animal(1000);
//		animal.move();
//		System.out.println("animal weight = " + animal.weight);
		Tiger tiger1 = new Tiger(498, "ao!");
		Tiger tiger2 = new Tiger(430, "ao!");
		Tiger tiger3 = new Tiger(500, "ao!");
		Tiger tiger4 = new Tiger(488, "ao!");
		Tiger tiger5 = new Tiger(590, "ao!");
		List<Tiger> list = new ArrayList<Tiger>();
		list.add(tiger1);
		list.add(tiger2);
		list.add(tiger3);
		list.add(tiger4);
		list.add(tiger5);
		System.out.println("sort before " + list);
		Collections.sort(list);
		System.out.println("sort after " + list);
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
}
