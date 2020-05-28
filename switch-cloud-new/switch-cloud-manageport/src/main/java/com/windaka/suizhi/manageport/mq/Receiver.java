package com.windaka.suizhi.manageport.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues="immediate_queue")
    public void rabbitReceive( Map<String,Object> params){
        log.info("************来消息了*****");
        System.out.println(params);
        //外网进行上传ftp---------------公安内网进行保存
    }

}
