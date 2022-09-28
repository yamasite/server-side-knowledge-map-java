package com.javadevmap.rabbitmqsender.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevmap.model.UserModel;

@Repository
public class RabbitmqSender {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private AmqpTemplate rabbitTemplete;
	
	public String sendString() {
		rabbitTemplete.convertAndSend("StringQueue","string message send");
		return "string send ok!";
	}
	
	public String sendString(int index) {
		rabbitTemplete.convertAndSend("StringQueue","string message send " + index);
		return "string send ok!";
	}
	
	public String sendObject() {
		UserModel user = new UserModel("javadev", 20, "java");
		rabbitTemplete.convertAndSend("ObjectQueue",user);
		return "object send ok!";
	}
	
	public String sendObjectJson() {
		try {
			UserModel user = new UserModel("javadev", 20, "java");
			String msg = mapper.writeValueAsString(user);
			rabbitTemplete.convertAndSend("ObjectJsonQueue",msg);
			return "object json send ok!";
		} catch (Exception e) {
			e.printStackTrace();
			return "object json send fail!";
		}
	}
	
	public String sendTopic() {
		rabbitTemplete.convertAndSend("TopicExchange", "Topic.MessageQueue", "topic message");
		
		rabbitTemplete.convertAndSend("TopicExchange", "Topic.other", "topic all");
		return "topic send ok!";
	}
	
	public String sendFanout() {
		rabbitTemplete.convertAndSend("FanoutExchange", "all", "fanout send");
		return "fanout send ok！";
	}
}
