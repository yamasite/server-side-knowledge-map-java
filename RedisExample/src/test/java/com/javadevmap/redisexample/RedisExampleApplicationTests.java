package com.javadevmap.redisexample;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javadevmap.redisexample.redisdao.RedisDao;
import com.javadevmap.redisexample.redisdao.impl.RedisDaoImpl;
import com.javadevmap.redisexample.redislock.RedisLock;
import com.javadevmap.redisexample.redislock.RedisSecKill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisExampleApplicationTests {
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private RedisLock redisLock;
	
	@Autowired
	private RedisSecKill redisSecKill;

//	@Test
//	public void testRedisString() {
//		redisDao.testRedisString();
//	}

//	@Test
//	public void testRedisList() {
//		redisDao.testRedisList();
//	}
	
//	@Test
//	public void testRedisHash() {
//		redisDao.testRedisHash();
//	}
	
//	@Test
//	public void testRedisSet() {
//		redisDao.testRedisSet();
//	}
	
//	@Test
//	public void testRedisZSet() {
//		redisDao.testRedisZSet();
//	}
	
//	@Test
//	public void testRedismulti() {
//		redisDao.testRedisMulti();
//	}
	
//	@Test
//	public void testRedisTransaction() {
//		redisDao.testRedisTransaction();
//	}
	
//	private void redisDoSmth() {
//		try {
//			if(redisLock.Lock()){
//				System.out.println(Thread.currentThread().getName() + " do some thing");
//				redisLock.unLock();
//				redisLock.success();
//			}else {
//				System.out.println(Thread.currentThread().getName() + " get lock fail");
//				redisLock.fail();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testRedisLock() {
//		ExecutorService eService = Executors.newFixedThreadPool(50);
//		for (int i = 0; i < 50; i++) {
//			eService.execute(new Runnable() {
//				@Override
//				public void run() {
//					redisDoSmth();
//				}
//			});
//		}
//		eService.shutdown();		
//		
//		try {
//			Thread.sleep(20000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	@Test
	public void testSecKill() {
		ExecutorService eService = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 50; i++) {
			eService.execute(new Runnable() {
				@Override
				public void run() {
					redisSecKill.redisSecKill();
				}
			});
		}
		eService.shutdown();		
		
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
