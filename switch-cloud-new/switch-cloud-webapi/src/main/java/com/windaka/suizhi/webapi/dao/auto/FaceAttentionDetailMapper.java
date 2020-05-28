package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.FaceAttentionDetail;
import com.windaka.suizhi.webapi.model.FaceAttentionDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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