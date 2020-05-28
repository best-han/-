package com.windaka.suizhi.manageport.mq;

import com.windaka.suizhi.manageport.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     * 功能描述: 生产请求信息
     * @auther: lixianhua
     * @date: 2020/3/26 9:36
     * @param:
     * @return:
     */
    public void rabbitMqSend(String str) {
        try{
            rabbitTemplate.convertAndSend("coreExchange","cloud_to_police",str);
        }catch (Exception e){
            log.error("[Sender.rabbitMqSend,参数：{},异常信息{}]",str,e.getMessage());
        }
    }

    /**
     * 功能描述: 生产抓拍信息
     * @auther: lixianhua
     * @date: 2020/3/26 9:37
     * @param:
     * @return:
     */
    public void rabbitCaptureSend(String str) {
        try{
            rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_POLICE_CAPTURE,str);
        }catch (Exception e){
            log.error("[Sender.rabbitMqSend,参数：{},异常信息{}]",str,e.getMessage());
        }
    }
}
