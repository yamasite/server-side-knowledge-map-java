package com.javadevmap.derive;

public class Fish extends Animal{
	public String livein = "water";
	
	public Fish(int weight,String livein) {
		super(weight);
		this.livein = livein;
	}

	@Override
	public void move() {
		System.out.println("fish can swim!");
	}
	
	public static void main(String[] args) {
//		Animal animal = new Animal(1000);
//		Tiger tiger = new Tiger(500, "ao!");
//		Fish fish = new Fish(10, "water");
//		
//		Animal[] animals = new Animal[3];
//		animals[0] = animal;
//		animals[1] = tiger;
//		animals[2] = fish;
//		
//		for(Animal temp:animals) {
//			System.out.println("animal weight = " + temp.weight);
//			temp.move();
//		}
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
}
