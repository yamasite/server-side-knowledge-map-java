package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Topic.AllQueue")
public class TopicAllReceiver {
	@RabbitHandler
	public void process(String message) {
		System.out.println("topicAllReceiver: " + message);
	}
}
