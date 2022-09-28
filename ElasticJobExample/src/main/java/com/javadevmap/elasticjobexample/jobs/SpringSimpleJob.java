package com.javadevmap.elasticjobexample.jobs;

import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.javadevmap.elasticjobexample.dao.OrderDao;

public class SpringSimpleJob implements SimpleJob{
	@Autowired
	private OrderDao dao;
	
	@Override  
    public void execute(ShardingContext shardingContext) {  
		int total = shardingContext.getShardingTotalCount();
		int cur = shardingContext.getShardingItem();
		System.out.println(String.format("SpringSimpleJob------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",  
                Thread.currentThread().getId(), total, cur));  
        System.out.println("SpringSimpleJob: " + Thread.currentThread().getId() + " cur = " + cur + " list is "+ dao.getTimeoutUserId(total, cur));
        
        /** 
         * 实际开发中，有了任务总片数和当前分片项，就可以对任务进行分片执行了 
         * 比如 SELECT * FROM user WHERE status = 0 AND MOD(id, shardingTotalCount) = shardingItem 
         */  
        
        
    }  
}
