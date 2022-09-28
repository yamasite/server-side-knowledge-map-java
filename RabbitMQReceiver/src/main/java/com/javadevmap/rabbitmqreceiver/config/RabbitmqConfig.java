package com.javadevmap.rabbitmqreceiver.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
	
	//message queue *************************
	@Bean
	public Queue StringQueue() {
		return new Queue("StringQueue");
	}
	
	//object queue ****************************
	@Bean
	public Queue ObjectQueue() {
		return new Queue("ObjectQueue");
	}
	
	//object json queue
	@Bean
	public Queue ObjectJsonQueue() {
		return new Queue("ObjectJsonQueue");
	}
	
	//topic ******************************
	@Bean
	public Queue TopicMessageQueue() {
		return new Queue("Topic.MessageQueue");
	}
	
	@Bean
	public Queue TopicAllQueue() {
		return new Queue("Topic.AllQueue");
	}
	
	//fanout *******************************
	@Bean
	public Queue AQueue() {
		return new Queue("AQueue");
	}
	
	@Bean
	public Queue BQueue() {
		return new Queue("BQueue");
	}

}
