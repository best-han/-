package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.FaceStrangerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaceStrangerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaceStrangerInfo record);

    int insertSelective(FaceStrangerInfo record);

    FaceStrangerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceStrangerInfo record);

    int updateByPrimaryKeyWithBLOBs(FaceStrangerInfo record);

    int updateByPrimaryKey(FaceStrangerInfo record);

    int deleteByCode(String personCode);
}