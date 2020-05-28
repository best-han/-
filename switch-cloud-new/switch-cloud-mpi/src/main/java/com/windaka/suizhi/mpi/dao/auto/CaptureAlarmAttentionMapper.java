package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CaptureAlarmAttention;
import com.windaka.suizhi.mpi.model.CaptureAlarmAttentionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CaptureAlarmAttentionMapper {
    int countByExample(CaptureAlarmAttentionExample example);

    int deleteByExample(CaptureAlarmAttentionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAlarmAttention record);

    int insertSelective(CaptureAlarmAttention record);

    List<CaptureAlarmAttention> selectByExample(CaptureAlarmAttentionExample example);

    CaptureAlarmAttention selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaptureAlarmAttention record, @Param("example") CaptureAlarmAttentionExample example);

    int updateByExample(@Param("record") CaptureAlarmAttention record, @Param("example") CaptureAlarmAttentionExample example);

    int updateByPrimaryKeySelective(CaptureAlarmAttention record);

    int updateByPrimaryKey(CaptureAlarmAttention record);
}