package com.javadevmap.templete;


class Tomato implements PrintInterface<Tomato>{
	@Override
	public void print() {
		System.out.println("It is Tomato!");
	}
}


public class CustomTemplete<T extends PrintInterface<T>> {
	public T data;
	
	public void print() {
		data.print();
	}

	public static void main(String[] args) {
		CustomTemplete<Tomato> customTemplete = new CustomTemplete<>();
		customTemplete.data = new Tomato();
		customTemplete.print();
	}
}
