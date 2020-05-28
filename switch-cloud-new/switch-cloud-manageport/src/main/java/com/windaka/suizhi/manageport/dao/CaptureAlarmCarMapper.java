package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.CaptureAlarmCar;

public interface CaptureAlarmCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAlarmCar record);

    int insertSelective(CaptureAlarmCar record);

    CaptureAlarmCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaptureAlarmCar record);

    int updateByPrimaryKey(CaptureAlarmCar record);
}