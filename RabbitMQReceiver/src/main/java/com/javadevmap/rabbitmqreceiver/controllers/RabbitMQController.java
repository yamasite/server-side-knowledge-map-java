package com.javadevmap.rabbitmqreceiver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.rabbitmqreceiver.result.Result;
import com.javadevmap.rabbitmqreceiver.result.ResultCode;

@RestController
@RequestMapping(value="/rabbitsender")
public class RabbitMQController {
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public Result<String> hello() {
		Result<String> result = new Result<String>(ResultCode.OK, "hello receiver");
		return result;
	}
}
