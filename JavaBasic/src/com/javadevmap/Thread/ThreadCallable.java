package com.javadevmap.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		TimeUnit.MILLISECONDS.sleep(1000);
		return Thread.currentThread().getName();
	}

	public static void main(String[] args) {
		ExecutorService eService = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<>();
		for(int i = 0;i < 10;i++) {
			list.add(eService.submit(new ThreadCallable()));
		}
		try {
			for(Future<String> fs:list) {
				System.out.println(fs.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eService.shutdown();
		}
		
	}
}
