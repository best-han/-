package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CaptureAlarmCar;
import com.windaka.suizhi.mpi.model.CaptureAlarmCarExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CaptureAlarmCarMapper {
    int countByExample(CaptureAlarmCarExample example);

    int deleteByExample(CaptureAlarmCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaptureAlarmCar record);

    int insertSelective(CaptureAlarmCar record);

    List<CaptureAlarmCar> selectByExample(CaptureAlarmCarExample example);

    CaptureAlarmCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaptureAlarmCar record, @Param("example") CaptureAlarmCarExample example);

    int updateByExample(@Param("record") CaptureAlarmCar record, @Param("example") CaptureAlarmCarExample example);

    int updateByPrimaryKeySelective(CaptureAlarmCar record);

    int updateByPrimaryKey(CaptureAlarmCar record);
}