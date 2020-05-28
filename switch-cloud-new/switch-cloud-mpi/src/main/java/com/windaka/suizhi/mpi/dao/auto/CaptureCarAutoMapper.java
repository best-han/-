package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CaptureCar;
import com.windaka.suizhi.mpi.model.CaptureCarExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CaptureCarAutoMapper {
    int countByExample(CaptureCarExample example);

    int deleteByExample(CaptureCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaptureCar record);

    int insertSelective(CaptureCar record);

    List<CaptureCar> selectByExample(CaptureCarExample example);

    CaptureCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaptureCar record, @Param("example") CaptureCarExample example);

    int updateByExample(@Param("record") CaptureCar record, @Param("example") CaptureCarExample example);

    int updateByPrimaryKeySelective(CaptureCar record);

    int updateByPrimaryKey(CaptureCar record);
}