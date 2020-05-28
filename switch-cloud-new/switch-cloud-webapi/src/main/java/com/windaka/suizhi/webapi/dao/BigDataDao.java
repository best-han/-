package com.windaka.suizhi.webapi.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 
 * @project: switch-cloud-manageport
 * @Description: 
 * @author: yangkai
 * @date: 2019年12月5日 下午4:38:28
 */
@Mapper
public interface BigDataDao {

    /**
     * 频繁夜归人员查询 hjt
     * @param params
     * @return
     */
    List<Map<String, Object>> getNightReturnPerson(@Param("params") Map<String, Object> params);

    /**
     * 单个频繁夜归人员查询 hjt
     * @param params
     * @return
     */
    List<Map<String, Object>> getNightReturnPersonByPersonCode(@Param("params") Map<String, Object> params);

    /**
     * 单个频繁夜归人员查询 hjt
     * @param params
     * @return
     */
    int totalGetNightReturnPersonByPersonCode(@Param("params") Map<String, Object> params);
    int totalGetNightReturnPersonByCode(Map<String, Object> params);

    /**
     * 频繁夜归车辆查询 hjt
     * @param params
     * @return
     */
    List<Map<String, Object>> getNightReturnCar(@Param("params") Map<String, Object> params);

    /**
     * 单个频繁夜归车辆查询 hjt
     * @param params
     * @return
     */
    List<Map<String, Object>> getNightReturnCarByCarNum(@Param("params") Map<String, Object> params);
    /**
     * 单个频繁夜归车辆查询 hjt
     * @param params
     * @return
     */
    int totalGetNightReturnCarByCarNum(@Param("params") Map<String, Object> params);

    /**
     * 用水数据查询 hjt
     * @param params
     * @return
     */
    List<Map<String, Object>> waterRate(@Param("params") Map<String, Object> params);

    /**
     * 群租房表数据清空
     */
    void deleteGroupRentalRoom();//hjt
    /**
     * 群租房插入 hjt
     * @param params
     * @return
     */
    int saveGroupRentalRoom(@Param("params") Map<String, Object> params);//hjt

    /**
     * 群租房查询  hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> groupRentalResearch(@Param("params") Map<String, Object> params);

    /**
     * 日租房查询  hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> dayRentalResearch(@Param("params") Map<String, Object> params);

    /**
     * 频繁夜归人员总数查询 hjt
     * @param params
     * @return
     */
    int totalPersonRows(@Param("params") Map<String, Object> params);

    /**
     * 频繁夜归车辆总数查询 hjt
     * @param params
     * @return
     */
    int totalCarRows(@Param("params") Map<String, Object> params);
    /**
     * 用水数据总数查询 hjt
     * @param params
     * @return
     */
    int totalWaterRows(@Param("params") Map<String, Object> params);

    /**
     * 群租房总数  hjt
     * @param params
     * @return
     */
    int totalGroupRentalRows(@Param("params") Map<String, Object> params);

    /**
     * 日租房总数 hjt
     * @param params
     * @return
     */
    int totalDayRentalResearch(@Param("params") Map<String, Object> params);
    
}
