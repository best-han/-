package com.windaka.suizhi.webapi.task;

import com.windaka.suizhi.webapi.dao.BaseRoomDao;
import com.windaka.suizhi.webapi.dao.IdleRoomDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class IdleRoomTask {

    @Autowired
    IdleRoomDao idleRoomDao;

    /**
     * 增加疑似闲置房屋  hjt
     *
     * @param
     * @return
     */
    public void idleRoomAdd() {
        log.info("****************执行idleRoomAdd开始*************");
        Map<String, Object> params = new HashMap<>();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        date.setTime(c.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        params.put("useDate", sdf.format(date));

        params.put("minimum", 0);//用水最低阈值

        List<Map<String, Object>> list = idleRoomDao.queryIdleRoomByWaterUse(params);
        int idleMonth=0;
        for (Map map : list) {
            List<Map<String, Object>> idleRooms = idleRoomDao.queryIdleRoom(map);
            if (idleRooms != null && idleRooms.size() > 0) {
                idleMonth= Integer.parseInt(idleRooms.get(0).get("idleMonth").toString());
                map.put("idleMonth",idleMonth+1);//原基础增加一个月
                idleRoomDao.updateIdleRoomByRoomCode(map);
            }else{
                map.put("idleMonth",1);
                idleRoomDao.addIdleRoom(map);
            }
        }
        log.info("****************执行idleRoomAdd成功*************");
    }

    /**
     * 增加闲置房屋疑似居住  hjt
     *
     * @param
     * @return
     */
    public void idleRoomLivedAdd() {
        log.info("****************执行idleRoomLivedAdd开始*************");
        Map<String, Object> params = new HashMap<>();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        date.setTime(c.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        params.put("useDate", sdf.format(date));

        params.put("minimum", 0);//用水最低阈值

        List<Map<String, Object>> list = idleRoomDao.queryIdleRoomLivedByWaterUse(params);
        int liveMonth=0;
        for (Map map : list) {
            List<Map<String, Object>> idleRooms = idleRoomDao.queryIdleRoom(map);
            if (idleRooms != null && idleRooms.size() > 0) {
                liveMonth= Integer.parseInt(idleRooms.get(0).get("liveMonth").toString());
                map.put("liveMonth",liveMonth+1);//原基础增加一个月
                idleRoomDao.updateIdleRoomByRoomCode(map);
            }else{
                map.put("liveMonth",1);
                idleRoomDao.addIdleRoom(map);
            }
        }
        log.info("****************执行idleRoomLivedAdd成功*************");
    }

    /**
     * 实有房产列表查询定时任务  hjt
     *
     * @param
     * @return
     */
    @Autowired
    BaseRoomDao baseRoomDao;
    public void jBaseHouseAdd() {
        log.info("****************执行jBaseHouseAdd开始*************");
        baseRoomDao.deletejBaseHouse();
        baseRoomDao.addjBaseHouse();
        log.info("****************执行jBaseHouseAdd成功*************");
    }

}
