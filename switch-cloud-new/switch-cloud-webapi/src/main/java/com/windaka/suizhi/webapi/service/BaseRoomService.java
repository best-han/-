package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import java.util.List;
import java.util.Map;

public interface BaseRoomService {

	/**
	 * @description: 地图-房屋楼栋列表
	 * @author wcl
	 * @date 2020/4/10
	 */

	List<Map<String,Object>> queryBuildingList(Map<String,Object> params);

	/**
	 * 小区、楼和单元树状展示 hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryCellByBuilding( Map<String,Object> params) throws OssRenderException;

	/**
	 * 查询该小区下的楼或单元列表  hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryBuildingCellByXq( Map<String,Object> params) throws OssRenderException;

	/**
	 * @description: 一房一档-房屋基础信息
	 * @author wcl
	 * @date 2020/4/14
	 */

	List<Map<String,Object>> queryRoomInfoByRoomCode(String roomCode);


	/**
	 * @description: 一房一档-人员信息列表
	 * @author wcl
	 * @date 2020/4/14
	 */

	Map<String,Object> queryPersonInfoByRoomCode(String roomCode);


	/**
	 * @description: 一房一档-车信息列表
	 * @author wcl
	 * @date 2020/4/14
	 */

	Map<String,Object> queryCarInfoByRoomCode(String roomCode);

	/**
	 * @description: 一房一档-犬类管理
	 * @author wcl
	 * @date 2020/4/15
	 */

	Map<String,Object> queryDogsByRoomCode(String roomCode);


	/**
	 * @description: 实有房产列表查询
	 * @author wcl
	 * @date 2020/4/15
	 */

	Map<String,Object> queryRoomInfoList(Map<String,Object> params);

/**
 * @author ：ygy
 * @date   ：2020/5/13 上午9:49
 * @description：  房产列表查询-按楼和单元分
 */
	Map<String,Object> houseRoomByBuildingList(Map<String,Object> params) throws OssRenderException;
}
