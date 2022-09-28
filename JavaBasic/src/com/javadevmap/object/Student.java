package com.javadevmap.object;

public class Student {
	private int age = 18;
	private String name = "todo";
	
	private static int count = 0;
	
	{
		age = 20;
		name = "Construct";
	}
	
	public static int getCount() {
		return count;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Student(int age,String name) {
		count++;
		this.age = age;
		this.name = name;
	}
	
	public Student() {
		count++;
		this.age = 0;
		this.name = "todo";
	}
	
	public static void main(String[] args) {
//		Student student = new Student();
//		//student.setAge(12);
//		//student.setName("xiaoming");
//		System.out.println("student age = " + student.getAge());
//		System.out.println("student name = " + student.getName());
		for(int i=0;i<10;i++) {
			Student student = new Student();
		}
		System.out.println("student count = " + Student.getCount());
	}
}
