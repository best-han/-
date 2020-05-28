package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CarGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarGroupMapper {
    int deleteByCode(String code);

    int insert(CarGroup record);

    int updateByCode(CarGroup record);

    List<CarGroup> selectCarGroupList(Map<String, Object> map);
}