package com.javadevmap.io;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.javadevmap.object.Person;

public class JavaSerialize {
	static public class Address implements Externalizable{
		public double longitude;
		public double latitude;
		public String name;
		public transient Person person;
		@Override
		public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
			longitude = arg0.readDouble();
			latitude = arg0.readDouble();
			name = (String)arg0.readObject();
		}
		@Override
		public void writeExternal(ObjectOutput arg0) throws IOException {
			arg0.writeDouble(longitude);
			arg0.writeDouble(latitude);
			arg0.writeObject(name);
		}
		
	}
	
//	static public class Address implements Serializable{
//		public double longitude;
//		public double latitude;
//		public String name;
//		public transient Person person;
//	}
	
	public static void write(String uri,Address address) {
		try {
			File file = new File(uri);
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(address);
			objectOutputStream.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Address Read(String uri) {
		Address address = null;
		try {
			File file = new File(uri);
			FileInputStream inputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			address = (Address)objectInputStream.readObject();
			objectInputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public static void main(String[] args) {
		Address address = new Address();
		address.latitude = 39.54;
		address.longitude = 116.23;
		address.name = "beijing";
		address.person = new Person(7, "xiaoming");
		write("d:\\test3.txt", address);
		Address result = Read("d:\\test3.txt");
		System.out.println("address name = " + result.name + " longitude = " + result.longitude + " latitude = " + result.latitude + " person = " + result.person);
	}
}

