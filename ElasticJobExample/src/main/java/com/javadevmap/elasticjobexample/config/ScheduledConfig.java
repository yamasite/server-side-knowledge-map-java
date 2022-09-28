package com.javadevmap.elasticjobexample.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Configuration
public class ScheduledConfig implements SchedulingConfigurer {
	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
	    scheduledTaskRegistrar.setScheduler(setTaskExecutors());
	}

	@Bean
	public Executor setTaskExecutors(){
	    return Executors.newScheduledThreadPool(3);
	}
}
