package com.windaka.suizhi.manageport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 * @author hjt
 */
@Slf4j
@Configuration
public class RabbitmqConfig {

	public static final String QUEUE_POLICE_CAPTURE = "QUEUE_POLICE_CAPTURE";

	//创建一个立即消费队列
	@Bean
	public Queue immediateQueue(){
		return new Queue("immediate_queue",true);//true是否持久化
	}
	@Bean
	public DirectExchange immediateExchange(){
		return new DirectExchange("immediate_exchange",true,false);
	}
	//把立即消费的队列和立即消费的exchange绑定在一起
	@Bean
	public Binding immediateBinding(){
		return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with("immdediate_routing_key");
	}

	@Bean
	public Queue queueFaceAlarm() {
		return new Queue(QUEUE_POLICE_CAPTURE, true); //队列持久
	}




}
