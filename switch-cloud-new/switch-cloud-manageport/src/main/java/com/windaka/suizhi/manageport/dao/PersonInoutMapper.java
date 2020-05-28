package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.PersonInout;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonInoutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonInout record);

    List<PersonInout> selectPersonInoutList(Map<String, Object> params);
}