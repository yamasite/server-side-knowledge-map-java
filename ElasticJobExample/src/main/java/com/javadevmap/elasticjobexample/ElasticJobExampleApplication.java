package com.javadevmap.elasticjobexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.javadevmap.elasticjobexample.model.mapper")
@SpringBootApplication
public class ElasticJobExampleApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(ElasticJobExampleApplication.class, args);
	}
}
