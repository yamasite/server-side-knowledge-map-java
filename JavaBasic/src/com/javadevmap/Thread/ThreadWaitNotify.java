package com.javadevmap.Thread;

public class ThreadWaitNotify {
	public boolean isReady = true;
	
	public synchronized void GetReady() {
		System.out.println("getready");
		isReady = true;
		notifyAll();
	}
	
	public synchronized void Go() {
		System.out.println("go");
		isReady = false;
		notifyAll();
	}
	
	public synchronized void waitReady() {
		try {
			while (!isReady) {
				System.out.println("waitReady");
				wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void waitGo() {
		try {
			while (isReady) {
				System.out.println("waitGo");
				wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ThreadWaitNotify waitNotify = new ThreadWaitNotify();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						waitNotify.waitReady();
						Thread.sleep(1000);
						waitNotify.Go();
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						waitNotify.waitGo();
						Thread.sleep(1000);
						waitNotify.GetReady();
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
}
