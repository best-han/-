package com.windaka.suizhi.webapi.dao;


import com.windaka.suizhi.webapi.model.CaptureAlarmPerson;
import com.windaka.suizhi.webapi.model.ext.ExtCaptureAlarmPerson;
import com.windaka.suizhi.webapi.model.ext.ExtHomeAlarmModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureAlarmPersonMapper {

    //    告警人员插入
    int insertAlarmPerson(Map<String, Object> params);

    //    webSocket告警记录列表查询
    List<Map<String, Object>> queryAlarmPersonList(Integer id);

    // 获取人员预警集合
    List<ExtCaptureAlarmPerson> selectPersonAlarmList(CaptureAlarmPerson alarm);

    // 根据id获取人员预警
    CaptureAlarmPerson getPersonById(Integer id);

    //根据id跟新人员预警
    int updatePersonAlarmById(CaptureAlarmPerson person);

    // 获取首页警告列表
    List<ExtHomeAlarmModel> getHomeList( ExtHomeAlarmModel model);

    // 获取人员报警数量
    Integer countPersonList(ExtCaptureAlarmPerson person);

    // 警务通 获取人员预警集合
    List<ExtCaptureAlarmPerson> selectAppPersonAlarmList(ExtCaptureAlarmPerson person);
}