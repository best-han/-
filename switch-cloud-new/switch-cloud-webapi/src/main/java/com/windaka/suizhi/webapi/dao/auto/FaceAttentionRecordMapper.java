package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.FaceAttentionRecord;
import com.windaka.suizhi.webapi.model.FaceAttentionRecordExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaceAttentionRecordMapper {
    int countByExample(FaceAttentionRecordExample example);

    int deleteByExample(FaceAttentionRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceAttentionRecord record);

    int insertSelective(FaceAttentionRecord record);

    List<FaceAttentionRecord> selectByExample(FaceAttentionRecordExample example);

    FaceAttentionRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceAttentionRecord record, @Param("example") FaceAttentionRecordExample example);

    int updateByExample(@Param("record") FaceAttentionRecord record, @Param("example") FaceAttentionRecordExample example);

    int updateByPrimaryKeySelective(FaceAttentionRecord record);

    int updateByPrimaryKey(FaceAttentionRecord record);
}