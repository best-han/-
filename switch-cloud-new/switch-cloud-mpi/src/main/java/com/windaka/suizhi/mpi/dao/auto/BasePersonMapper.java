package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.BasePerson;
import com.windaka.suizhi.mpi.model.BasePersonExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BasePersonMapper {
    int countByExample(BasePersonExample example);

    int deleteByExample(BasePersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasePerson record);

    int insertSelective(BasePerson record);

    List<BasePerson> selectByExample(BasePersonExample example);

    BasePerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasePerson record, @Param("example") BasePersonExample example);

    int updateByExample(@Param("record") BasePerson record, @Param("example") BasePersonExample example);

    int updateByPrimaryKeySelective(BasePerson record);

    int updateByPrimaryKey(BasePerson record);
}