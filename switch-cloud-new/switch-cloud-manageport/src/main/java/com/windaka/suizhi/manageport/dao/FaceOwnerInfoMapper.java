package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.FaceOwnerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FaceOwnerInfoMapper {
    int delete(Map<String, Object> params);

    int insert(FaceOwnerInfo record);

    int insertSelective(FaceOwnerInfo record);

    FaceOwnerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceOwnerInfo record);

    int updateByPrimaryKeyWithBLOBs(FaceOwnerInfo record);

    int updateByPrimaryKey(FaceOwnerInfo record);
}