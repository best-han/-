package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BaseCommunity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseCommunityMapper {
    /*
     *添加小区信息 ygy
     * */
    int insertCommunity(BaseCommunity baseCommunity);

    /*
     * 删除小区 by code  ygy
     * */
    int deleteByCode(String code);

    /*
     * 更新小区 by code ygy
     * */
    int updateByCode(BaseCommunity baseCommunity);


    /*
     * 查询小区列表 by keyword ygy
     * */
    List<BaseCommunity> selectCommunityList(Map<String, Object> params);
}