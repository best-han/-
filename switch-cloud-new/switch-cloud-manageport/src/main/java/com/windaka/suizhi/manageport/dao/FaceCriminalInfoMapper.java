package com.windaka.suizhi.manageport.dao;


import com.windaka.suizhi.manageport.model.FaceCriminalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FaceCriminalInfoMapper {

    int insert(FaceCriminalInfo record);

    int delete(Map<String, Object> params);

}