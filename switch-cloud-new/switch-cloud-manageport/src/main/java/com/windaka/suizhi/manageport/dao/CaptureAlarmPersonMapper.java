package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.CaptureAlarmPerson;

public interface CaptureAlarmPersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAlarmPerson record);

    int insertSelective(CaptureAlarmPerson record);

    CaptureAlarmPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaptureAlarmPerson record);

    int updateByPrimaryKey(CaptureAlarmPerson record);
}