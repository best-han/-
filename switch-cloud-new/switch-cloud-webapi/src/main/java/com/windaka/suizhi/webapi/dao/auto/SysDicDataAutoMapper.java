package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.SysDicData;
import com.windaka.suizhi.webapi.model.SysDicDataExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysDicDataAutoMapper {
    int countByExample(SysDicDataExample example);

    int deleteByExample(SysDicDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDicData record);

    int insertSelective(SysDicData record);

    List<SysDicData> selectByExample(SysDicDataExample example);

    SysDicData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysDicData record, @Param("example") SysDicDataExample example);

    int updateByExample(@Param("record") SysDicData record, @Param("example") SysDicDataExample example);

    int updateByPrimaryKeySelective(SysDicData record);

    int updateByPrimaryKey(SysDicData record);
}