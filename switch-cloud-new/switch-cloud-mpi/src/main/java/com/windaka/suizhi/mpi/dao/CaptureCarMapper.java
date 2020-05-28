package com.windaka.suizhi.mpi.dao;

import com.windaka.suizhi.mpi.model.HomeAlarmModel;
import com.windaka.suizhi.mpi.model.ext.ExtCaptureCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureCarMapper {

    // 获取最大车辆抓拍记录
    @Select("select MAX(id) from capture_car")
    Integer getMaxId();

    // 获取最近的预警车辆信息
    List<ExtCaptureCar> queryCarForAlarm(int lastId);

    //    webSocket告警记录列表查询（车辆）
    List<Map<String,Object>> queryCapCarList(Integer id);
}