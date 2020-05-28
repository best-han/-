package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.FaceOwnerInfo;
import com.windaka.suizhi.webapi.model.FaceOwnerInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FaceOwnerInfoMapper {
    int countByExample(FaceOwnerInfoExample example);

    int deleteByExample(FaceOwnerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceOwnerInfo record);

    int insertSelective(FaceOwnerInfo record);

    List<FaceOwnerInfo> selectByExampleWithBLOBs(FaceOwnerInfoExample example);

    List<FaceOwnerInfo> selectByExample(FaceOwnerInfoExample example);

    FaceOwnerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceOwnerInfo record, @Param("example") FaceOwnerInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") FaceOwnerInfo record, @Param("example") FaceOwnerInfoExample example);

    int updateByExample(@Param("record") FaceOwnerInfo record, @Param("example") FaceOwnerInfoExample example);

    int updateByPrimaryKeySelective(FaceOwnerInfo record);

    int updateByPrimaryKeyWithBLOBs(FaceOwnerInfo record);

    int updateByPrimaryKey(FaceOwnerInfo record);
}