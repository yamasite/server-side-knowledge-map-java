package com.javadevmap.redisexample.redislock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisSecKill {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final int TOTALCOUNT = 10;
	
	public void redisSecKill() {
		Long ranking = 0L;
		int index = 0;
		System.out.println(Thread.currentThread().getName() + " redisSecKill start************");
		boolean type = false;
		long starttime = System.currentTimeMillis();
		redisTemplate.setEnableTransactionSupport(true);
		List<Object> list = null;
		try {
			while(list == null || list.size()==0 || (Long)list.get(0)==0) {
				System.out.println(Thread.currentThread().getName() + " redisSecKill index value = " + (++index));
				long now = System.currentTimeMillis();
				long costTime = now - starttime;
				if(costTime > 1000) {
					System.out.println(Thread.currentThread().getName() + " redisSecKill timeout break*********");
					type = false;
					break;
				}
				String temp = redisTemplate.opsForValue().get("redisSecKill");
				if(Long.valueOf(temp)>=TOTALCOUNT) {
					System.out.println(Thread.currentThread().getName() + " redisSecKill out total break*********");
					type = false;
					break;
				}
				redisTemplate.watch("redisSecKill");
				redisTemplate.multi();
				redisTemplate.opsForValue().increment("redisSecKill", 1);
				redisTemplate.opsForValue().get("redisSecKill");
				//Thread.sleep(20);
				list = redisTemplate.exec();
				costTime = now - starttime;
				System.out.println(Thread.currentThread().getName() + " costime = " + costTime);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if(list!=null && list.size()>0) {
			String temp = (String)list.get(1);
			ranking = Long.valueOf(temp);
			if(ranking>TOTALCOUNT) {
				System.out.println(Thread.currentThread().getName() + " not ok and value = " + ranking);
				type = false;
			}else {
				type = redisTemplate.opsForValue().setIfAbsent("redisSecKill success:" + ranking, Thread.currentThread().getName());
			}
		}
		
		if(type) {
			System.out.println(Thread.currentThread().getName() + " redisSecKill ok");
			redisTemplate.opsForList().rightPush("redisSecKillList",Thread.currentThread().getName());
		}else {
			System.out.println(Thread.currentThread().getName() + " redisSecKill fail");
		}
	}
}
