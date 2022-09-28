package com.javadevmap.Thread;

import java.util.concurrent.CountDownLatch;

public class ThreadCountDownLatch {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		System.out.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out
							.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
					Thread.sleep(5000);
					System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out
							.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
					Thread.sleep(5000);
					System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() + " wait time is " + System.currentTimeMillis());
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
	}
}
