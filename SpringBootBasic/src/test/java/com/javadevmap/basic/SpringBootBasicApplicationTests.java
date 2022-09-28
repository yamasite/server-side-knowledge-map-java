package com.javadevmap.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javadevmap.basic.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBasicApplicationTests {
	@Autowired
	private UserRepository repository;
	
	@Test
	public void contextLoads() {
		System.out.println(repository.findById(3));
		System.out.println(repository.findByName("java"));
		System.out.println(repository.findByAgeBetween(10, 20));
	}

}
