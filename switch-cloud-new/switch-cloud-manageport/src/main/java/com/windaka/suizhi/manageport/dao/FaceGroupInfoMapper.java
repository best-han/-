package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.FaceGroupInfo;

public interface FaceGroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaceGroupInfo record);

    int insertSelective(FaceGroupInfo record);

    FaceGroupInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceGroupInfo record);

    int updateByPrimaryKeyWithBLOBs(FaceGroupInfo record);

    int updateByPrimaryKey(FaceGroupInfo record);
}