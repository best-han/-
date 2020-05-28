package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.EventLevelMapping;
import com.windaka.suizhi.webapi.model.EventLevelMappingExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface EventLevelMappingMapper {
    int countByExample(EventLevelMappingExample example);

    int deleteByExample(EventLevelMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventLevelMapping record);

    int insertSelective(EventLevelMapping record);

    List<EventLevelMapping> selectByExample(EventLevelMappingExample example);

    EventLevelMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventLevelMapping record, @Param("example") EventLevelMappingExample example);

    int updateByExample(@Param("record") EventLevelMapping record, @Param("example") EventLevelMappingExample example);

    int updateByPrimaryKeySelective(EventLevelMapping record);

    int updateByPrimaryKey(EventLevelMapping record);
}