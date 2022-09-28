package com.javadevmap.derive;

public abstract class Animal {
	public int weight;
	
	public Animal(int weight) {
		this.weight = weight;
	}
	
	public void move() {
		System.out.println("animal can move!");
	}
	
	public abstract void eat();
}
