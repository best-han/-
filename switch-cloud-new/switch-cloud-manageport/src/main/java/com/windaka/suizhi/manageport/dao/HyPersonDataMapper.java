package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.HyPersonData;

public interface HyPersonDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HyPersonData record);

    int insertSelective(HyPersonData record);

    HyPersonData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HyPersonData record);

    int updateByPrimaryKey(HyPersonData record);
}