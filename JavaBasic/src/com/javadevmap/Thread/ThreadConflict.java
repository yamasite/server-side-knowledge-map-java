package com.javadevmap.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConflict{
	private int sum;
	private Lock lock = new ReentrantLock();
	
	public int getSum(int start,int end) {
		sum = 0;
		for(int i=start;i<end;i++) {
			sum +=i;
		}
		return sum;
	}
	
	public synchronized int getSumBySyn(int start,int end) {
		sum = 0;
		for(int i=start;i<end;i++) {
			sum +=i;
		}
		return sum;
	}
	
	public int getSumByLock(int start,int end) {
		lock.lock();
		try {
			sum = 0;
			for(int i=start;i<end;i++) {
				sum +=i;
			}
			return sum;
		} finally {
			lock.unlock();
		}
	}
	
	public int getSumByTryLock(int start,int end) {
		try {
			if(lock.tryLock(1, TimeUnit.SECONDS)) {
				sum = 0;
				for(int i=start;i<end;i++) {
					sum +=i;
				}
			}
			return sum;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ThreadConflict threadConflict = new ThreadConflict();
		System.out.println("main thread sum = " + threadConflict.getSum(0, 1000));
		
		ExecutorService eService = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++) {
			eService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " sum = " + threadConflict.getSum(0, 1000));
					//System.out.println(Thread.currentThread().getName() + " sum = " + threadConflict.getSumBySyn(0, 1000));
					//System.out.println(Thread.currentThread().getName() + " sum = " + threadConflict.getSumByLock(0, 1000));
					//System.out.println(Thread.currentThread().getName() + " sum = " + threadConflict.getSumByTryLock(0, 1000));
				}
			});
		}
		eService.shutdown();
	}
}
