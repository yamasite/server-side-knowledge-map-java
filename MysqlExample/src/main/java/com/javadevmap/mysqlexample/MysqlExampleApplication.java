package com.javadevmap.mysqlexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("com.javadevmap.mysqlexample.mapper")
public class MysqlExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlExampleApplication.class, args);
	}
}
