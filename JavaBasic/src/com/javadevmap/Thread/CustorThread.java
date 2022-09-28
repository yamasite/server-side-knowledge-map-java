package com.javadevmap.Thread;

public class CustorThread extends Thread{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(this);
	}
	
	
	public static void main(String[] args) {
		CustorThread thread1 = new CustorThread();
		thread1.setPriority(Thread.MAX_PRIORITY);
		
		CustorThread thread2 = new CustorThread();
		thread1.setPriority(Thread.MIN_PRIORITY);
		
		thread1.start();
		//thread2.setDaemon(true);
		thread2.start();
	}
}
