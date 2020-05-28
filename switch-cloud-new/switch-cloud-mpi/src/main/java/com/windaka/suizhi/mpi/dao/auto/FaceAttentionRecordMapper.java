package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.FaceAttentionRecord;
import com.windaka.suizhi.mpi.model.FaceAttentionRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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