package com.javadevmap.rabbitmqreceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevmap.model.UserModel;

@Component
@RabbitListener(queues = "ObjectJsonQueue")
public class ObjectJsonReceiver {
	ObjectMapper mapper = new ObjectMapper();
	
	@RabbitHandler
	public void process(String message) {
		System.out.println("ObjectJsonReceiver: " + message);
		try {
			UserModel user = mapper.readValue(message, UserModel.class);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
