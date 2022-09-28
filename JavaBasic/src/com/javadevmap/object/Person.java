package com.javadevmap.object;

public class Person {
	public long id;
	public String name;
	public Eyes eyes = new Eyes();

	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "id = " + this.id + " name = " + this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Person person = (Person) obj;
		if ((this.id == person.id) && (this.name == person.name)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}

	public static class Eyes {
		public String left = "zuoyan";
		public String right = "youyan";
	}

	public static void main(String[] args) {
		Person person1 = new Person(1, "xiaoming");
		Person person2 = new Person(1, "xiaoming");
		System.out.println("person 1 == person 2 = " + (person1 == person2));
		System.out.println("person 1 equals person 2 = " + (person1.equals(person2)));
	}
}
