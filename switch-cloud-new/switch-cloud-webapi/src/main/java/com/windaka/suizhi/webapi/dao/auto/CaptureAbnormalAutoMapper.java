package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.CaptureAbnormal;
import com.windaka.suizhi.webapi.model.CaptureAbnormalExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CaptureAbnormalAutoMapper {
    int countByExample(CaptureAbnormalExample example);

    int deleteByExample(CaptureAbnormalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAbnormal record);

    int insertSelective(CaptureAbnormal record);

    List<CaptureAbnormal> selectByExample(CaptureAbnormalExample example);

    CaptureAbnormal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaptureAbnormal record, @Param("example") CaptureAbnormalExample example);

    int updateByExample(@Param("record") CaptureAbnormal record, @Param("example") CaptureAbnormalExample example);

    int updateByPrimaryKeySelective(CaptureAbnormal record);

    int updateByPrimaryKey(CaptureAbnormal record);
}