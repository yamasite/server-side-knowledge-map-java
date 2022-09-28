package com.javadevmap.elasticjobexample.model.mapper;

import java.util.List;
import java.util.Map;

import com.javadevmap.elasticjobexample.model.OrderJob;
import com.javadevmap.elasticjobexample.model.OrderJobExample;
import com.javadevmap.elasticjobexample.model.OrderStatis;

public interface OrderManualMapper {
	public OrderStatis getOrderStatis(OrderJobExample example);
	public List<Long> getUnpaidUser(Map<Object, Object> map);
	public List<Long> getStatisUser(Map<Object, Object> map);
	public List<OrderJob> getStatisOrder(Map<Object, Object> map);
}
