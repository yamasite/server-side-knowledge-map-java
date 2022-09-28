package com.javadevmap.elasticjobexample.dao.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javadevmap.elasticjobexample.dao.OrderDao;
import com.javadevmap.elasticjobexample.model.OrderJob;
import com.javadevmap.elasticjobexample.model.OrderJobExample;
import com.javadevmap.elasticjobexample.model.OrderStatis;
import com.javadevmap.elasticjobexample.model.UserStatis;
import com.javadevmap.elasticjobexample.model.mapper.OrderJobMapper;
import com.javadevmap.elasticjobexample.model.mapper.OrderManualMapper;
import com.javadevmap.elasticjobexample.model.mapper.UserStatisManualMapper;

@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private OrderManualMapper manualMapper;
	
	@Autowired
	private OrderJobMapper mapper;
	
	@Autowired
	private UserStatisManualMapper userMapper;

	@Override
	public OrderStatis getStatis() {
		OrderJobExample example = new OrderJobExample();
		OrderJobExample.Criteria criteria = example.createCriteria();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(format.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.andCreatetimeGreaterThanOrEqualTo(date);
		OrderStatis statis = manualMapper.getOrderStatis(example);
		return statis;
	}
	
	public List<Long> getTimeoutUserId(int total,int cur){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total",total);
		map.put("cur", cur);
		List<Long> list = manualMapper.getUnpaidUser(map);
		return list;	
	}
	
	public List<OrderJob> getStatisList(int total,int cur){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total",total);
		map.put("cur", cur);
		List<Long> userlist = manualMapper.getStatisUser(map);
		
		if(!userlist.isEmpty()) {
			Map<Object, Object> listmap = new HashMap<Object, Object>();
			listmap.put("list",userlist);
			List<OrderJob> list = manualMapper.getStatisOrder(listmap);
			return list;
		}
		return null;
	}
	
	public void completeStatis(List<OrderJob> list) {
		for (OrderJob orderJob : list) {
			orderJob.setStatis(true);
			mapper.updateByPrimaryKey(orderJob);
		}
	}
	
	public boolean TableExist() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DATE, -1);  
        date = calendar.getTime(); 
		String tableName = format.format(date);
		int count = userMapper.existTable(tableName);
		System.out.println(tableName);
		if(count ==0) {
			userMapper.createTable(tableName);
		}
		return true;
	}
	
	public void insertUserStatis(UserStatis userStatis) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DATE, -1);  
        date = calendar.getTime(); 
		String tableName = format.format(date);
		System.out.println(tableName);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("tableName",tableName);
		map.put("userStatis", userStatis);
		userMapper.insertUserStatis(map);
	}
	
}
