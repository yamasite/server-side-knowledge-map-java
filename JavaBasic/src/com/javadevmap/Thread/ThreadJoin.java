package com.javadevmap.Thread;

public class ThreadJoin {
	
	public static void testThreadJoin() {
		System.out.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
					Thread t2 = new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								System.out
										.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
								Thread.sleep(5000);
								System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					});
					t2.start();
					t2.join();
					System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		t1.start();
		System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
	}

	public static void testJoin() {
		System.out.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out
							.println(Thread.currentThread().getName() + " start time is " + System.currentTimeMillis());
					Thread.sleep(5000);
					System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
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
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		try {
			t1.start();
			t1.join();
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end time is " + System.currentTimeMillis());
	}

	public static void main(String[] args) {
		testThreadJoin();
	}
}
