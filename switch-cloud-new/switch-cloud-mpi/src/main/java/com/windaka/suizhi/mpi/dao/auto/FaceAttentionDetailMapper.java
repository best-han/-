package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.FaceAttentionDetail;
import com.windaka.suizhi.mpi.model.FaceAttentionDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FaceAttentionDetailMapper {
    int countByExample(FaceAttentionDetailExample example);

    int deleteByExample(FaceAttentionDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceAttentionDetail record);

    int insertSelective(FaceAttentionDetail record);

    List<FaceAttentionDetail> selectByExample(FaceAttentionDetailExample example);

    FaceAttentionDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceAttentionDetail record, @Param("example") FaceAttentionDetailExample example);

    int updateByExample(@Param("record") FaceAttentionDetail record, @Param("example") FaceAttentionDetailExample example);

    int updateByPrimaryKeySelective(FaceAttentionDetail record);

    int updateByPrimaryKey(FaceAttentionDetail record);
}