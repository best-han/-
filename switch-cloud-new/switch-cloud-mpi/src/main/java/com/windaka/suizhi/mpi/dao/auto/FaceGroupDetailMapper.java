package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.FaceGroupDetail;
import com.windaka.suizhi.mpi.model.FaceGroupDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FaceGroupDetailMapper {
    int countByExample(FaceGroupDetailExample example);

    int deleteByExample(FaceGroupDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceGroupDetail record);

    int insertSelective(FaceGroupDetail record);

    List<FaceGroupDetail> selectByExampleWithBLOBs(FaceGroupDetailExample example);

    List<FaceGroupDetail> selectByExample(FaceGroupDetailExample example);

    FaceGroupDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceGroupDetail record, @Param("example") FaceGroupDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") FaceGroupDetail record, @Param("example") FaceGroupDetailExample example);

    int updateByExample(@Param("record") FaceGroupDetail record, @Param("example") FaceGroupDetailExample example);

    int updateByPrimaryKeySelective(FaceGroupDetail record);

    int updateByPrimaryKeyWithBLOBs(FaceGroupDetail record);

    int updateByPrimaryKey(FaceGroupDetail record);
}