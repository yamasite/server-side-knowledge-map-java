package com.javadevmap.elasticjobexample.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.javadevmap.elasticjobexample.dao.OrderDao;
import com.javadevmap.elasticjobexample.model.OrderJob;
import com.javadevmap.elasticjobexample.model.UserStatis;

public class SpringDataflowJob implements DataflowJob {
	@Autowired
	private OrderDao dao;
	
	@Override
	public List fetchData(ShardingContext shardingContext) {
		int total = shardingContext.getShardingTotalCount();
		int cur = shardingContext.getShardingItem();
		System.out.println(String.format("SpringDataflowJob fetchData ------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",  
                Thread.currentThread().getId(),total, cur)); 
		return dao.getStatisList(total, cur);
	}

	@Transactional
	@Override
	public void processData(ShardingContext shardingContext, List data) {
		int total = shardingContext.getShardingTotalCount();
		int cur = shardingContext.getShardingItem();
		System.out.println(String.format("SpringDataflowJob processData ------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",  
                Thread.currentThread().getId(), total, cur));  
		
		if(dao.TableExist()) {
			Map<Long, UserStatis> map = new HashMap<Long, UserStatis>();
			for (Object object : data) {
				OrderJob orderJob = (OrderJob)object;
				if( map.containsKey(orderJob.getUserid()) ) {
					UserStatis userStatis = map.get(orderJob.getUserid());
					userStatis.setStatisPrice(userStatis.getStatisPrice() + orderJob.getPrice());
				}else {
					UserStatis userStatis = new UserStatis();
					userStatis.setUserid(orderJob.getUserid());
					userStatis.setStatisPrice(orderJob.getPrice());
					map.put(userStatis.getUserid(), userStatis);
				}
				orderJob.setStatis(true);
			}
			for (UserStatis userStatis : map.values()) {
				dao.insertUserStatis(userStatis);
			}
		}
		
		dao.completeStatis(data);
	}
}
