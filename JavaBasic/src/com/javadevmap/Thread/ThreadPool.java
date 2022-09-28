package com.javadevmap.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static void testCachedPool() {
		ExecutorService eService = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++) {
			eService.execute(new ThreadRunnable(i*100,i*1000));
		}
		eService.shutdown();
	}
	
	public static void testFixedPool() {
		ExecutorService eService = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++) {
			eService.execute(new ThreadRunnable(i*100,i*1000));
		}
		eService.shutdown();
	}
	
	public static void main(String[] args) {
		//testCachedPool();
		testFixedPool();
	}
}
