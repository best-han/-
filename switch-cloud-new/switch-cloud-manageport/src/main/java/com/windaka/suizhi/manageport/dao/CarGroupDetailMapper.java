package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CarGroupDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarGroupDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarGroupDetail record);

    int insertSelective(CarGroupDetail record);

    CarGroupDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarGroupDetail record);

    int updateByPrimaryKey(CarGroupDetail record);

    List<CarGroupDetail> selectCarGroupDetailList(Map<String, Object> params);
}