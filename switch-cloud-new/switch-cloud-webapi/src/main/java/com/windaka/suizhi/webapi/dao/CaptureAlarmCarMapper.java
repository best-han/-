package com.windaka.suizhi.webapi.dao;


import com.windaka.suizhi.webapi.model.CaptureAlarmCar;
import com.windaka.suizhi.webapi.model.ext.ExtCaptureAlarmCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureAlarmCarMapper {
    //    webSocket告警记录列表查询（车辆）
    List<Map<String,Object>> queryAlarmCarList(Integer id);
    // 获取车辆预警集合
    List<ExtCaptureAlarmCar> selectCarAlarmList(CaptureAlarmCar car);
    // 根据ID获取车辆预警
    CaptureAlarmCar getCarById(Integer id);
    // 根据ID更新车辆预警
    int updateCarAlarmById(CaptureAlarmCar car);
    // 获取车辆预警数量
    Integer countCarList(ExtCaptureAlarmCar car);
    // 警务通 获取车辆预警信息
    List<ExtCaptureAlarmCar> selectAppCarAlarmList(ExtCaptureAlarmCar car);
}