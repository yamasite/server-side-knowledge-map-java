package com.javadevmap.serviceprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@MapperScan("com.javadevmap.serviceprovider.model.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class SpringCloudServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceProviderApplication.class, args);
	}
}
