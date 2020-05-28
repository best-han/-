package com.windaka.suizhi.webapi.config;

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
	public static final String EXCHANGE_POLICE_FACEALARM = "my-mq-exchange_POLICE_FACEALARM";
	public static final String QUEUE_TIPS = "QUEUE_TIPS";
	public static final String QUEUE_PICS = "QUEUE_PICS";
	public static final String QUEUE_STREET_ABNORMAL = "QUEUE_STREET_ABNORMAL";
	public static final String QUEUE_POLICE_FACEALARM = "QUEUE_POLICE_FACEALARM";
	public static final String QUEUE_YS_FACEALARM = "QUEUE_YS_FACEALARM";
	public static final String ROUTINGKEY_POLICE_FACEALARM = "spring-boot-routingKey_POLICE_FACEALARM";
	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(EXCHANGE_POLICE_FACEALARM,true,false);
	}
	/**
	 *  * 获取队列Tips
	 *  * @return
	 *  */
	@Bean
	public Queue queueTips() {
	 	return new Queue(QUEUE_TIPS, true); //队列持久
	 }
	@Bean
	public Queue queuePics() {
		return new Queue(QUEUE_PICS, true); //队列持久
	}
	@Bean
	public Queue queueFaceAlarm() {
		return new Queue(QUEUE_POLICE_FACEALARM, true); //队列持久
	}

	@Bean
	public Binding bindingFaceAlarm(){
	 	return BindingBuilder.bind(queueFaceAlarm()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY_POLICE_FACEALARM);
	 }





}
