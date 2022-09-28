package com.javadevmap.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadInterrupt {
	public static void testThreadInterrupt() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (!Thread.interrupted()) {
						System.out.println("running......" + Thread.interrupted());
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
		try {
			Thread.sleep(5000);
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testThreadPoolShutdownNow() {
		ExecutorService eService = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++) {
			eService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						while (!Thread.interrupted()) {
							System.out.println("running......" + Thread.interrupted());
							Thread.sleep(1000);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		eService.shutdown();
		try {
			Thread.sleep(3000);
			eService.shutdownNow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//testThreadInterrupt();
		testThreadPoolShutdownNow();
	}
}
