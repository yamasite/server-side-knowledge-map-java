package com.javadevmap.exception;

import java.io.FileReader;

public class FileExceptionDemo {
	
	public static String readFile(){
		boolean bool = true;
		StringBuilder builder = new StringBuilder();
		try {
			FileReader fReader = new FileReader("d:\\test.txt");
			char[] cs = new char[10];
			while (fReader.read(cs)!=-1) {
				builder.append(cs);
				cs = new char[10];
			}
			fReader.close();
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		} finally {
			if(bool) {
				System.out.println("read file ok!");
			}else {
				System.out.println("read file fail!");
				builder.replace(0, builder.length(), "fail");
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(readFile());
	}
}
