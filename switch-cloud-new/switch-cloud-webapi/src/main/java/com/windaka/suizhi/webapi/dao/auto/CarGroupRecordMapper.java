package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.CarGroupRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarGroupRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarGroupRecord record);

    int insertSelective(CarGroupRecord record);

    CarGroupRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarGroupRecord record);

    int updateByPrimaryKey(CarGroupRecord record);
}