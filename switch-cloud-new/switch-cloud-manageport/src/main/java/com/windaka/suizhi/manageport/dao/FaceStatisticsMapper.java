package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.FaceStatistics;

public interface FaceStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaceStatistics record);

    int insertSelective(FaceStatistics record);

    FaceStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceStatistics record);

    int updateByPrimaryKeyWithBLOBs(FaceStatistics record);

    int updateByPrimaryKey(FaceStatistics record);
}