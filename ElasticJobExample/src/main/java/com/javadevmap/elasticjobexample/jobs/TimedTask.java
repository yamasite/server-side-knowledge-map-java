package com.javadevmap.elasticjobexample.jobs;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.javadevmap.elasticjobexample.dao.OrderDao;

//@EnableAsync
@Component
public class TimedTask {
	@Autowired
	private OrderDao dao;
	
//	//@Async
//	@Scheduled(cron="0/10 * * * * ?")
//	public void task1() {
//		System.out.println(Thread.currentThread().getName() + " task1: " + new Date());
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}
//	
//	//@Async
//	@Scheduled(cron="0/5 * * * * ?")
//	public void task2() {
//		System.out.println(Thread.currentThread().getName() + " task2: " + new Date());
//	}
	
	@Scheduled(cron="0/10 * * * * ?")
	public void getStatis() {
		System.out.println(dao.getStatis());
	}
}
