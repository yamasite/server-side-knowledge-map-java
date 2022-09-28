package com.javadevmap.redisexample.redislock;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisLock {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private String lockKey = "redislock";
	
	private volatile boolean locked = false;
	
	private int expireMsecs = 10*1000;
	
	private int timeoutMsecs = 2*1000;
	
	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 50;
	 
	public boolean Lock() throws InterruptedException  {
		int timeout = timeoutMsecs;
		int index = 0;
		Random random = new Random();
		while(timeout>0) {
			System.out.println(Thread.currentThread().getName() + " index value = " + (++index));
			long expires = System.currentTimeMillis() + expireMsecs + 1;
			String expiresStr = String.valueOf(expires);
			if (redisTemplate.opsForValue().setIfAbsent(lockKey, expiresStr)) {
				System.out.println(Thread.currentThread().getName() + " locked setIfAbsent");
				locked = true;
                return true;
            }
			
			String currentValueStr = redisTemplate.opsForValue().get(lockKey);
			if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {

                String oldValueStr = redisTemplate.opsForValue().getAndSet(lockKey, expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                	System.out.println(Thread.currentThread().getName() + " locked getAndSet");
                    locked = true;
                    return true;
                }
            }
            int temp = random.nextInt(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
            timeout -= temp;
            Thread.sleep(temp);
		}
		return false;
	}
	
	public void unLock() {
		if (locked) {
			System.out.println(Thread.currentThread().getName() + " unlock");
			locked = false;
            redisTemplate.delete(lockKey);
            
        }
	}
	
	public void success() {
		redisTemplate.opsForList().rightPush("redislocksuccesslist", Thread.currentThread().getName());
	}
	
	public void fail() {
		redisTemplate.opsForList().rightPush("redislockfaillist", Thread.currentThread().getName());
	}
}
