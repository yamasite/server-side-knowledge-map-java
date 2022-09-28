package com.javadevmap.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLoaclDemo extends Thread{
	public static ThreadLocal<Integer> num = new ThreadLocal<Integer>() {

		@Override
		protected Integer initialValue() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	};
		
	public int getNum() {
		int ret = num.get();
		num.set(ret+1);
		return ret;
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++) {
			System.out.println(Thread.currentThread().getName() + " num = " + getNum());
		}
	}
	
	public static void main(String[] args) {
		ExecutorService eService = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			eService.execute(new ThreadLoaclDemo());
		}
		eService.shutdown();
	}
}
