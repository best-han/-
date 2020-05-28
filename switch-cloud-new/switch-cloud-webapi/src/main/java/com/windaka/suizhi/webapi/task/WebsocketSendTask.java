package com.windaka.suizhi.webapi.task;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.feign.MpiClient;
import com.windaka.suizhi.webapi.service.CaptureAlarmCarService;
import com.windaka.suizhi.webapi.service.CaptureAlarmPersonService;
import com.windaka.suizhi.webapi.service.CaptureCarService;
import com.windaka.suizhi.webapi.service.CapturePersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WebsocketSendTask {

    @Autowired
    private MpiClient mpiClient;

    @Autowired
    private CaptureAlarmCarService captureAlarmCarService;

    @Autowired
    private CaptureAlarmPersonService captureAlarmPersonService;

    @Autowired
    private CapturePersonService capturePersonService;
    @Autowired
    private CaptureCarService captureCarService;

    public void executeInternal() throws OssRenderException {
        log.info("WebsocketSendTask发送开始*************");
        Map params=new HashMap();
//        List alarmCarList = captureAlarmCarService.queryCapCarList(params);
//        alarmCarList.forEach(temp -> {//分条发送
//            String str = JSON.toJSONString(temp);
//            mpiClient.sendWebsocketMessage(str);
//        });

//        List alarmPersonList = captureAlarmPersonService.queryAlarmPersonList(params);//告警人员列表
//        alarmPersonList.forEach(temp -> {//分条发送
//            String str = JSON.toJSONString(temp);
//            mpiClient.sendWebsocketMessage(str);
//        });

        List capPersonList = capturePersonService.queryCapPersonList(params);//推送抓拍人
        capPersonList.forEach(temp -> {//分条发送
            String str = JSON.toJSONString(temp);
            mpiClient.sendWebsocketMessage(str);
        });
        List capCarList=captureCarService.queryCapCarList(params);//推送抓拍车
        capCarList.forEach(temp -> {//分条发送
            String str = JSON.toJSONString(temp);
            mpiClient.sendWebsocketMessage(str);
        });
    }
}
