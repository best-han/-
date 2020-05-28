package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CarGroupRecord;
import com.windaka.suizhi.mpi.model.CarGroupRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CarGroupRecordMapper {
    int countByExample(CarGroupRecordExample example);

    int deleteByExample(CarGroupRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarGroupRecord record);

    int insertSelective(CarGroupRecord record);

    List<CarGroupRecord> selectByExample(CarGroupRecordExample example);

    CarGroupRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarGroupRecord record, @Param("example") CarGroupRecordExample example);

    int updateByExample(@Param("record") CarGroupRecord record, @Param("example") CarGroupRecordExample example);

    int updateByPrimaryKeySelective(CarGroupRecord record);

    int updateByPrimaryKey(CarGroupRecord record);
}