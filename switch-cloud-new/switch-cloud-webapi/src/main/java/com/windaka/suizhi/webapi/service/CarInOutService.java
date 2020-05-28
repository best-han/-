package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.api.common.Page;
import com.windaka.suizhi.common.exception.OssRenderException;
import io.micrometer.shaded.org.pcollections.PMap;

import java.util.Map;

/**
 * @Description 车辆出入感知
 * @Author wcl
 * @Date 2020/4/10
 */
public interface CarInOutService {

	/**
	 * @description: 车辆出入信息(车流)统计
	 * @author wcl
	 * @Date 2020/4/3
	 */
	Page<Map<String,Object>> queryCarAccessList(Map<String,Object> params);

	/*
	zdq 车辆出入列表 es
	 */
	Map<String,Object> queryCarAccessListElasticSearch(Map<String,Object> params);
	Map<String,Object> queryCarAccessDetailsByCarNumByES(Map<String,Object> params) throws OssRenderException;


	/**
	 * @description: 查询某辆车的出入记录详情
	 * @author wcl
	 * @date 2020/4/10
	 */

	Map<String,Object> queryCarAccessDetailsByCarNum(Map<String,Object> params);


	/**
	 * @description: 高频出入车辆
	 * @author wcl
	 * @date 2020/4/8
	 */
	Map<String,Object> queryCarHighAccessList(Map<String,Object> params);

	/*
	zdq 高频出入车辆 es
	 */
	Map<String,Object> queryCarHighAccessListElasticSearch(Map<String,Object> params);
}
