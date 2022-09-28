package com.javadevmap.ElasticJobExample;

import com.javadevmap.elasticjobexample.dao.OrderDao;
import com.javadevmap.elasticjobexample.model.OrderStatis;
import com.javadevmap.elasticjobexample.model.UserStatis;
import com.javadevmap.elasticjobexample.model.mapper.OrderManualMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticJobExampleApplicationTests {
	@Autowired
	private OrderDao dao;

	@Test
	public void contextLoads() {
		//dao.getStatisList(3, 2);
		//dao.TableExist();
//		UserStatis userStatis = new UserStatis();
//		userStatis.setUserid(2L);
//		userStatis.setStatisPrice(20);
//		dao.insertUserStatis(userStatis);
	}
}
