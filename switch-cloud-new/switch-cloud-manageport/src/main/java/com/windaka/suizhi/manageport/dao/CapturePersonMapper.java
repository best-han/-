package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CapturePerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CapturePersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CapturePerson record);

    List<CapturePerson> selectCapturePersonList(Map<String, Object> params);
}