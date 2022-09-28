package com.javadevmap.rabbitmqsender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
	
	//message queue *************************
	@Bean
	public Queue StringQueue() {
		return new Queue("StringQueue");
	}
	
	//object queue ****************************
	@Bean
	public Queue ObjectQueue() {
		return new Queue("ObjectQueue");
	}
	
	//object json queue
	@Bean
	public Queue ObjectJsonQueue() {
		return new Queue("ObjectJsonQueue");
	}
	
	//topic ******************************
	@Bean
	public Queue TopicMessageQueue() {
		return new Queue("Topic.MessageQueue");
	}
	
	@Bean
	public Queue TopicAllQueue() {
		return new Queue("Topic.AllQueue");
	}
	
	@Bean
	public TopicExchange TopicExchange() {
		return new TopicExchange("TopicExchange");
	}
	
	@Bean
	public Binding bindingExchangeMessage(Queue TopicMessageQueue,TopicExchange TopicExchange) {
		return BindingBuilder.bind(TopicMessageQueue).to(TopicExchange).with("Topic.MessageQueue");
	}
	
	@Bean
	public Binding bindingExchangeAll(Queue TopicAllQueue,TopicExchange TopicExchange) {
		return BindingBuilder.bind(TopicAllQueue).to(TopicExchange).with("Topic.#");
	}
	
	//fanout *******************************
	@Bean
	public Queue AQueue() {
		return new Queue("AQueue");
	}
	
	@Bean
	public Queue BQueue() {
		return new Queue("BQueue");
	}
	
	@Bean
	public FanoutExchange FanoutExchange() {
		return new FanoutExchange("FanoutExchange");
	}
	
	@Bean
    Binding bindingExchangeA(Queue AQueue,FanoutExchange FanoutExchange) {
        return BindingBuilder.bind(AQueue).to(FanoutExchange);
    }
	
	@Bean
    Binding bindingExchangeB(Queue BQueue,FanoutExchange FanoutExchange) {
        return BindingBuilder.bind(BQueue).to(FanoutExchange);
    }
	
}
