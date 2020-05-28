package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface PersonInoutMapper {
    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午2:04
     * @description：  人员出入记录
     */
    List<Map<String,Object>>  personAccessList(Map<String,Object> params);
    int personAccessListOptimizeCount(Map<String,Object> params);
    List<Map<String,Object>>  personAccessListOptimize(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午4:17
     * @description：  高频出入人员
     */
    List<Map<String,Object>> personSenseHighAccess(Map<String,Object> params);
    List<Map<String,Object>> singlePersonAccessByCode(String personCode);

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:07
     * @description：  今日人员出入数量
     */
    int selectPersonAccessNum(Map<String,Object> params);
}