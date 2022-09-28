package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "AQueue")
public class AQueueReceiver {
	@RabbitHandler
	public void process(String message) {
		System.out.println("AQueueReceiver: " + message);
	}
}
