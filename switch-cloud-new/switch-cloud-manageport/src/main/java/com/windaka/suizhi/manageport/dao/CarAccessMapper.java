package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CarAccess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarAccessMapper {

    /*
     * 抓拍车辆出入 新增 ygy
     * */
    int insertCarAccess(CarAccess carAccess);

    /*
     * 抓拍车辆出入 删除 ygy
     * */
    int deleteById(String id);

    /*
     * 抓拍车辆 列表查询 ygy
     * */
    List<CarAccess> selectCarAccessList(Map<String, Object> params);
}