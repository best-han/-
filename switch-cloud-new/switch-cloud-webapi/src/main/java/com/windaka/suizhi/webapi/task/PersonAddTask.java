package com.windaka.suizhi.webapi.task;

import com.windaka.suizhi.webapi.dao.CapturePersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PersonAddTask {
    @Autowired
    private CapturePersonMapper capturePersonMapper;

    public void executeInternal() {
        log.info("***********************执行[疑似迁入]人员查询定时任务***********************");

//        // 1 清理 之前感知到  16天后 并未感知到到人员
        capturePersonMapper.deletePersonAdd();

        //2 查询疑似新增人 personId  capDays
        List<Map<String, Object>> lists = capturePersonMapper.personAddedList();

        //3 处理新增人数据
        if (null != lists && lists.size() > 0) {
            for (Map<String, Object> list : lists) {
                //根据personCode 查找新增人员的信息
                String personCode = list.get("personId").toString();
                String capDay = list.get("capDays").toString();
                List<Map<String, Object>> persons = capturePersonMapper.selectPersonInfoByCode(personCode);
                Map<String, Object> person = persons.get(0);
                list.put("personCode", personCode);
                list.put("communityName", person.get("community_name"));
                list.put("communityCode", person.get("community_code"));
                list.put("capSex", person.get("cap_sex"));
                list.put("capAge", person.get("cap_age"));
                list.put("faceImage", person.get("cap_person_image"));
                list.put("bodyImage", person.get("cap_image"));
                list.put("capDay", capDay);
            }
            capturePersonMapper.emptyPersonAddApp();
            //4 将疑似新增人员写入 疑似新增人员表中
            for (Map<String, Object> list : lists) {

//                判断新增人员表中是否有此人
                String personCode = list.get("personCode").toString();
                List person = capturePersonMapper.selectPersonAddByCode(personCode);
                if (null != person && person.size() > 0) {

                    //若存在此人 则只更新抓拍天数
                    capturePersonMapper.updatePersonAddTable(list);

                    //若存在此人 若已发短信 则在15天后将信息发送状态改为 未发送
                    capturePersonMapper.updatePersonAddTable15(personCode);

                } else {
                    //若不存在 则直接插入
                    capturePersonMapper.insertPersonAddTable(list);
                    capturePersonMapper.insertPersonAddApp(list);
                }

            }
        }
    }

}
