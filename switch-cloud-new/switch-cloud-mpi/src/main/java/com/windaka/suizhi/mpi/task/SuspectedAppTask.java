package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.mpi.dao.SuspectedAppDao;
import com.windaka.suizhi.mpi.model.AppModel;
import com.windaka.suizhi.mpi.model.HtUser;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.websocket.AppSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 *@description: 疑似新增人 迁出人 迁出车 警务通app推送
 *@author: zdq
 *@time: 5/18/20 11:30 AM
 *
 */

@Slf4j
@Service
public class SuspectedAppTask {

    @Autowired
    private SuspectedAppDao suspectedAppDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AppSocket appSocket;

    public void executeInternal()
    {
        //疑似新增人 new
        List<Map<String,Object>> suspectedAddPersonNewList=suspectedAppDao.querySuspectedAddPersonAppGroupByXqCode();
        //疑似迁出人 new
        List<Map<String,Object>> suspectedQuitPersonNewList=suspectedAppDao.querySuspectedQuitPersonAppGroupByXqCode();
        //app推送
        log.info("疑似迁入人推送---start");
        sendAppInform(suspectedAddPersonNewList,"1","2");
        log.info("疑似迁入人推送---end");
        log.info("--------------------");
        log.info("疑似迁出人推送---start");
        sendAppInform(suspectedQuitPersonNewList,"2","2");
        log.info("疑似迁出人推送---end");
        log.info("--------------------");
    }

    public void sendAppInform(List<Map<String,Object>> informList,String type,String subType)
    {
        for (Map<String, Object> informT : informList) {
            String xqCode=informT.get("xqCode").toString();
            int totalNew=Integer.parseInt(informT.get("totalNew").toString());
            log.info("新增数量："+totalNew);
            List<HtUser> userList = userService.getUserListByXqCode(xqCode);
            AppModel app = new AppModel();
            app.setType(type);
            app.setSubType(subType);
            app.setNum(totalNew);
            String appMessage = JSON.toJSONString(app);
            // 警务通socket
            appSocket.sendMessageToUser(appMessage, userList);
        }
    }
}
