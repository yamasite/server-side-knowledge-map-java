package com.javadevmap.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIO {
	
	public static void write(String uri) {
		try {
			File file = new File(uri);
			FileOutputStream outputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
			outputStreamWriter.write("你好，世界！\nhello world!");
			outputStreamWriter.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String Read(String uri) {
		StringBuilder builder = new StringBuilder();
		try {
			File file = new File(uri);
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			while (inputStreamReader.ready()) {
				char[] c = new char[128];
				inputStreamReader.read(c);
				builder.append(c);
			}
			inputStreamReader.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		write("d:\\test2.txt");
		System.out.println(Read("d:\\test2.txt"));
	}
}
