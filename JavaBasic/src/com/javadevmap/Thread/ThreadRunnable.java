package com.javadevmap.Thread;

public class ThreadRunnable implements Runnable{
	private int start;
	private int end;
	public ThreadRunnable(int start,int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for(int i = start;i<=end;i++) {
			sum+=i;
		}
		System.out.println("thread is " + Thread.currentThread().getName()+ " start = " + start + " end = " + end + " sum = " + sum);
	}
	
	public static void main(String[] args) {
		ThreadRunnable runnable = new ThreadRunnable(100,1000);
		runnable.run();
		
		Thread thread = new Thread(new ThreadRunnable(200,2000));
		thread.start();
	}
}
