package com.javadevmap.mybatis.jvisualvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JvmCpuTest {
	private static void singleThread(){
		try {
			long time = 1;
			while(time>0) {
				time = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void multiThread(){
		ExecutorService eService = Executors.newFixedThreadPool(2);
		for(int i=0;i<2;i++) {
			eService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						long time = 1;
						while(time>0) {
							time = System.currentTimeMillis();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		eService.shutdown();
	}
	
	public static void main(String[] args) {
		//singleThread();
		multiThread();
	}
}
