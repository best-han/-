package com.windaka.suizhi.webapi.task;

import com.windaka.suizhi.webapi.dao.BigDataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class GroupRentalRoomAddTask {

    @Autowired
    BigDataDao bigDataDao;

    /**
     * 群租房添加  hjt
     *
     * @param
     * @return
     */
    public void executeInternal() {
        log.info("****************执行GroupRentalRoomAddTask开始*************");
        bigDataDao.deleteGroupRentalRoom();
        Map<String, Object> params = new HashMap<>();
        params.put("associateNum",5);
        bigDataDao.saveGroupRentalRoom(params);
        log.info("****************执行GroupRentalRoomAddTask成功*************");

    }


}
