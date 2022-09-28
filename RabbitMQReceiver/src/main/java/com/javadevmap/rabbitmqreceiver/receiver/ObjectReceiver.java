package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javadevmap.model.UserModel;

@Component
@RabbitListener(queues = "ObjectQueue")
public class ObjectReceiver {
	
	@RabbitHandler
	public void process(UserModel user) {
		System.out.println("ObjectReceiver: " + user.toString());
	}
}
