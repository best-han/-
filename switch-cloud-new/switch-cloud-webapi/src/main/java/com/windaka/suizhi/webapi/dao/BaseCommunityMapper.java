package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface BaseCommunityMapper {

    /**
     * @author ：ygy
     * @date   ：2020/4/2 下午4:05
     * @description：  查询小区信息列表
     */
    List<Map<String,Object>>  communityList(Map<String,Object> params);
}