package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CarAccess;
import com.windaka.suizhi.mpi.model.CarAccessExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CarAccessMapper {
    int countByExample(CarAccessExample example);

    int deleteByExample(CarAccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarAccess record);

    int insertSelective(CarAccess record);

    List<CarAccess> selectByExample(CarAccessExample example);

    CarAccess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarAccess record, @Param("example") CarAccessExample example);

    int updateByExample(@Param("record") CarAccess record, @Param("example") CarAccessExample example);

    int updateByPrimaryKeySelective(CarAccess record);

    int updateByPrimaryKey(CarAccess record);
}