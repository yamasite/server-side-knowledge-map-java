package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Topic.MessageQueue")
public class TopicMessageReceiver {
	@RabbitHandler
	public void process(String message) {
		System.out.println("topicMessageQueue: " + message);
	}
}
