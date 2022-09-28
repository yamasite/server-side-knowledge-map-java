package com.javadevmap.rabbitmqsender.controllers;

import static org.mockito.Matchers.intThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.rabbitmqsender.result.Result;
import com.javadevmap.rabbitmqsender.result.ResultCode;
import com.javadevmap.rabbitmqsender.sender.RabbitmqSender;
import com.javadevmap.rabbitmqsender.sender.RabbitmqSender2;

@RestController
@RequestMapping(value="/rabbitsender")
public class RabbitMQController {
	@Autowired
	private RabbitmqSender sender;
	
	@Autowired
	private RabbitmqSender2 sender2;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public Result<String> hello() {
		Result<String> result = new Result<>(ResultCode.OK, "hello sender");
		return result;
	}
	
	@RequestMapping(value="/sendMessage",method=RequestMethod.GET)
	public Result<String> sendMessage() {
		String ret = sender.sendString();
		Result<String> result = new Result<>(ResultCode.OK, ret);
		return result;
	}
	
	@RequestMapping(value="/sendMultiMessage",method=RequestMethod.GET)
	public Result<String> sendMultiMessage() {
		for(int i=0;i<10;i++) {
			sender.sendString(i);
			sender2.sendString(i);
		}
		Result<String> result = new Result<>(ResultCode.OK, "OK");
		return result;
	}
	
	@RequestMapping(value="/sendObject",method=RequestMethod.GET)
	public Result<String> sendObject() {
		String ret = sender.sendObject();
		Result<String> result = new Result<>(ResultCode.OK, ret);
		return result;
	}
	
	@RequestMapping(value="/sendObjectJson",method=RequestMethod.GET)
	public Result<String> sendObjectJson() {
		String ret = sender.sendObjectJson();
		Result<String> result = new Result<>(ResultCode.OK, ret);
		return result;
	}
	
	@RequestMapping(value="/sendTopic",method=RequestMethod.GET)
	public Result<String> sendTopic() {
		String ret = sender.sendTopic();
		Result<String> result = new Result<>(ResultCode.OK, ret);
		return result;
	}
	
	@RequestMapping(value="/sendFanout",method=RequestMethod.GET)
	public Result<String> sendFanout() {
		String ret = sender.sendFanout();
		Result<String> result = new Result<>(ResultCode.OK, ret);
		return result;
	}
}
