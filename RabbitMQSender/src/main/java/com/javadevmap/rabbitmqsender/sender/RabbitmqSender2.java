package com.javadevmap.rabbitmqsender.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RabbitmqSender2 {
	@Autowired
	private AmqpTemplate rabbitTemplete;
	
	public String sendString(int index) {
		rabbitTemplete.convertAndSend("StringQueue","string message2 send " + index);
		return "string send ok!";
	}
}
