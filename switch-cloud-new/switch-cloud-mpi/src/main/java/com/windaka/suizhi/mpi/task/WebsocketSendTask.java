package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.mpi.service.CaptureCarService;
import com.windaka.suizhi.mpi.service.CapturePersonService;
import com.windaka.suizhi.mpi.websocket.SocketServer;
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
            log.info("***************************websocket抓拍人推送信息***************"+str);
            sendWebsocketMessage(str);
        });
        List capCarList=captureCarService.queryCapCarList(params);//推送抓拍车
        capCarList.forEach(temp -> {//分条发送
            String str = JSON.toJSONString(temp);
            log.info("***************************websocket抓拍车推送信息***************"+str);
            sendWebsocketMessage(str);
        });
    }

    public void sendWebsocketMessage(String msg)
    {
        //log.info("接受到的提示消息为："+msg);
        JSONObject contentJson = JSONObject.parseObject(msg);
        //json对象转Map
        Map<String,Object> contentMap = (Map<String,Object>)contentJson;
        for(Map.Entry<String, List<Map<String,Object>>> entry: SocketServer.websocketXq.entrySet()) {
            List<Map<String,Object>> xqList=entry.getValue();
            for(int i=0;i<xqList.size();i++){
                Map<String,Object> xq=xqList.get(i);
                if(((String)xq.get("xqCode")).equals((String) contentMap.get("xqCode"))){
                    log.info("发送消息为："+msg);
                    SocketServer.sendMessage(msg,entry.getKey());
                }
            }
        }
    }
}
