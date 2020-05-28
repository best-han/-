package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface BaseRoomDao {

	/**
	 * @description: 地图-房屋楼栋列表
	 * @author wcl
	 * @date 2020/4/10
	 */

	List<Map<String,Object>> queryBuildingList(@Param("params") Map<String,Object> params);

	/**
	 * @author ：ygy
	 * @date   ：2020/4/3 上午9:12
	 * @description：  根据roomCode 查找房间名
	 */
	String queryHouseNameByRoomCode(String roomCode);

	/**
	 * @author ：ygy
	 * @date   ：2020/4/9 上午8:58
	 * @description：  实有房屋数量
	 */
	int selectRoomNum(Map<String,Object> params);

	/**
	 * 在room表中查所有小区 hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryXqListInRoom(Map<String,Object> params);

	/**
	 * 在room表中查楼栋 hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryBuildingsByXqCodeInRoom(Map<String,Object> params);

	/**
	 * 在room表中查单元 hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryUnitsByXqCodeAndBuildingCodeInRoom(Map<String,Object> params);

	/**
	 * @description: 一房一档-房屋基础信息
	 * @author wcl
	 * @date 2020/4/14
	 */
	List<Map<String,Object>> queryRoomInfoByRoomCode(@Param("roomCode") String roomCode);


	/**
	 * @description: 一房一档-人员信息列表
	 * @author wcl
	 * @date 2020/4/14
	 */
	List<Map<String,Object>> queryPersonInfoByRoomCode(@Param("roomCode") String roomCode);


	/**
	 * @description: 一房一档-车信息列表
	 * @author wcl
	 * @date 2020/4/14
	 */
	List<Map<String,Object>> queryCarInfoByRoomCode(@Param("roomCode") String roomCode);

	/**
	 * @description: 一房一档-犬类管理
	 * @author wcl
	 * @date 2020/4/15
	 */
	List<Map<String,Object>> queryDogsByRoomCode(@Param("roomCode") String roomCode);


	/**
	 * @description: 实有房产列表查询
	 * @author wcl
	 * @date 2020/4/15
	 */
	List<Map<String,Object>> queryRoomInfoList(@Param("params") Map<String,Object> params);

	/**
	 * @description: 房间关联人数
	 * @author wcl
	 * @date 2020/4/15
	 */
	int queryLivePersonNumByRoomCode(String roomCode);

	/**
	 * @description: 房间关联车数
	 * @author wcl
	 * @date 2020/4/15
	 */
	int queryRoomCarNumByRoomCode(String roomCode);

	/**
	 * @description: 实有房产列表查询定时任务
	 * @author wcl
	 * @date 2020/4/30
	 */
	int addjBaseHouse();

	/**
	 * @description: 定时任务 清空表数据
	 * @author wcl
	 * @date 2020/4/30
	 */
	int deletejBaseHouse();

	/**
	 * @author ：ygy
	 * @date   ：2020/5/13 上午9:47
	 * @description：  通过楼栋code查询单元列表
	 */
	List<Map<String,Object>> queryCellListByBuildingCode(Map<String,Object> params);
	/**
	 * @author ：ygy
	 * @date   ：2020/5/13 上午9:47
	 * @description：  通过楼栋code 和单元code 查询房屋列表
	 */
	List<Map<String,Object>> queryRoomListByCode(Map<String,Object> params);

}
