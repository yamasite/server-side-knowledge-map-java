package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "BQueue")
public class BQueueReceiver {
	@RabbitHandler
	public void process(String message) {
		System.out.println("BQueueReceiver: " + message);
	}
}
