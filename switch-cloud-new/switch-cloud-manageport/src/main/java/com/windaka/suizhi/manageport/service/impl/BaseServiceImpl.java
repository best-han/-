package com.windaka.suizhi.manageport.service.impl;

import com.windaka.suizhi.manageport.mq.Sender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BaseServiceImpl
 * @Description 通用服务类
 * @Author lixianhua
 * @Date 2020/3/24 16:45
 * @Version 1.0
 */
@Service
public class BaseServiceImpl {


    @Autowired
    private Sender sender;

    public final static String url_head = "http://172.16.1.150:"; //测试

    /**
     * 功能描述: 发送消息到MQ
     *
     * @auther: lixianhua
     * @date: 2020/3/24 16:23
     * @param:
     * @return:
     */
    public boolean senderMessage(String type, String url, String params) {
        try {
            if (StringUtils.isNotBlank(type) || StringUtils.isNotBlank(url) || StringUtils.isNotBlank(params)) {
//                sender.rabbitMqSend(url_head + url + "|" + type + "|" + params + ";");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 功能描述: 发送抓拍信息
     *
     * @auther: lixianhua
     * @date: 2020/3/26 9:33
     * @param:
     * @return:
     */
    public boolean senderCapture(String id, String personCode, String type) {
        if (StringUtils.isNotBlank(id) || StringUtils.isNotBlank(personCode) || StringUtils.isNotBlank(type)) {
            sender.rabbitCaptureSend(id + ";" + personCode + ";" + type);
            return true;
        }
        return false;
    }

}
