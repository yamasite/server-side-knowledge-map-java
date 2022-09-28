package com.javadevmap.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadReadWriteLock {
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private int num = 0;
	private int count = 0;
	
	public int read() {
		lock.readLock().lock();
		try {
			synchronized (this) {
				count++;
			}
			return num;
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void write() {
		lock.writeLock().lock();
		try {
			synchronized (this) {
				num++;
			}
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public static void main(String[] args) {
		ThreadReadWriteLock readWriteLock = new ThreadReadWriteLock();
		ExecutorService eService = Executors.newCachedThreadPool();
		for(int i=0;i<3;i++) {
			eService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						while (true) {
							Thread.sleep(2000);
							System.out.println(Thread.currentThread().getName() + " num = " + readWriteLock.read());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		eService.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(3000);
						readWriteLock.write();
						System.out.println(Thread.currentThread().getName() + " write");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		eService.shutdown();
	}
}
