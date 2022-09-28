package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "StringQueue")
public class MessageReceiver2 {
	@RabbitHandler
	public void process(String message) {
		System.out.println("messageReceiver2: " + message);
	}
}
