package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface CarAccessMapper {

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:14
     * @description：  今日车辆出入数量
     */
    int selectCarAccessNum(Map<String,Object> params);
}