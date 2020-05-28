package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CaptureAlarmPerson;
import com.windaka.suizhi.mpi.model.CaptureAlarmPersonExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CaptureAlarmPersonMapper {
    int countByExample(CaptureAlarmPersonExample example);

    int deleteByExample(CaptureAlarmPersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAlarmPerson record);

    int insertSelective(CaptureAlarmPerson record);

    List<CaptureAlarmPerson> selectByExample(CaptureAlarmPersonExample example);

    CaptureAlarmPerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaptureAlarmPerson record, @Param("example") CaptureAlarmPersonExample example);

    int updateByExample(@Param("record") CaptureAlarmPerson record, @Param("example") CaptureAlarmPersonExample example);

    int updateByPrimaryKeySelective(CaptureAlarmPerson record);

    int updateByPrimaryKey(CaptureAlarmPerson record);
}