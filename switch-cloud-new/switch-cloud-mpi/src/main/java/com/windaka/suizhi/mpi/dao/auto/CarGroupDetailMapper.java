package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CarGroupDetail;
import com.windaka.suizhi.mpi.model.CarGroupDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CarGroupDetailMapper {
    int countByExample(CarGroupDetailExample example);

    int deleteByExample(CarGroupDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarGroupDetail record);

    int insertSelective(CarGroupDetail record);

    List<CarGroupDetail> selectByExample(CarGroupDetailExample example);

    CarGroupDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarGroupDetail record, @Param("example") CarGroupDetailExample example);

    int updateByExample(@Param("record") CarGroupDetail record, @Param("example") CarGroupDetailExample example);

    int updateByPrimaryKeySelective(CarGroupDetail record);

    int updateByPrimaryKey(CarGroupDetail record);
}