package com.javadevmap.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleIO {
	
	public static void testConsoleStreamIO() {
		try {
			char c;
			InputStream in = System.in;
			do {
				c = (char) in.read();
				System.out.println(c);
			} while (c!='q');
			
		} catch (Exception e) {
			System.err.println("catch Exception");
		}
	}
	
	public static void testConsoleBufferIO() {
		try {
			char c;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			do {
				c = (char) in.read();
				System.out.println(c);
			} while (c!='q');
			
		} catch (Exception e) {
			System.err.println("catch Exception");
		}
	}
	
	public static void main(String[] args) {
		//testConsoleStreamIO();
		testConsoleBufferIO();
	}
}
