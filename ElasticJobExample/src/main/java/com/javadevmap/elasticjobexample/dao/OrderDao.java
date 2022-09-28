package com.javadevmap.elasticjobexample.dao;

import java.util.List;

import com.javadevmap.elasticjobexample.model.OrderJob;
import com.javadevmap.elasticjobexample.model.OrderStatis;
import com.javadevmap.elasticjobexample.model.UserStatis;

public interface OrderDao {
	public OrderStatis getStatis();
	public List<Long> getTimeoutUserId(int total,int cur);
	public List<OrderJob> getStatisList(int total,int cur);
	public void completeStatis(List<OrderJob> list);
	public boolean TableExist();
	public void insertUserStatis(UserStatis userStatis);
}
