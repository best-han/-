package com.windaka.suizhi.webapi.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @Description:实有车辆模块
* @Author: Ms.wcl
* @Date: 2020/4/3
*/

@Mapper
public interface BaseCarDao {

	/**
	 * @description: 车辆列表查询
	 * @author wcl
	 * @Date 2020/4/3
	 */
	List<Map<String,Object>> queryCarInfoList(@Param("params")Map<String,Object> params);

	/**
	 * @description: 查询车辆品牌
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryCarBrandName(@Param("brandNameLikeStr") String brandNameLikeStr);

	/**
	 * @description: 查询车辆类型
	 * @author wcl
	 * @date 2020/4/16
	 */
	List<Map<String,Object>> queryCarTypeName();

	/**
	 * @description: 查询车辆颜色
	 * @author wcl
	 * @date 2020/5/7
	 */
	List<Map<String,Object>> queryCarColorName();

	/**
	 * @description:  车辆列表总条数(按权限)
	 * @author wcl
	 * @date 2020/4/3
	 */
	int totalRows(@Param("params") Map<String, Object> params);

	/**
	 * @description: 常住人口车辆
	 * @author wcl
	 * @date 2020/4/7
	 */
	int permanentPopulationCarNum(@Param("params") Map<String, Object> params);

	/**
	 * @description: 流动人口车辆
	 * @author wcl
	 * @date 2020/4/7
	 */
	int floatingPopulationCarNum(@Param("params") Map<String, Object> params);

	/**
	 * @description: 本月新增车辆
	 * @author wcl
	 * @date 2020/4/7
	 */
	int monthAddCarCarNum(@Param("params") Map<String, Object> params);


	/**
	 * @author ：ygy
	 * @date   ：2020/4/9 上午9:31
	 * @description：  实有车辆数量
	 */
	int selectCarNum(Map<String,Object> params);



	/**
	 * @description: 根据车牌号查询车和车主基础信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryPersonInfoAndOwnerInfoByCarNum(@Param("carNum")String carNum);


	/**
	 * @description: 根据车牌号查询布控和异常行为标签
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryAbnormalTypeByCarNum(@Param("params") Map<String, Object> params);
	List<Map<String,Object>> queryHighAccessByCarNum(@Param("params") Map<String, Object> params);

	/**
	 * @description: 根据车牌号查询roomCode
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryRoomCodeByCarNum(@Param("carNum")String carNum);

	/**
	 * @description: 根据roomCode查询房屋信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryRoomInfoByRoomCode(@Param("roomCode")String roomCode);


	/**
	 * @description: 根据车牌号查询停车位
	 * @author wcl
	 * @date 2020/4/14
	 */
	List<Map<String,Object>> queryParkingByCarNum(@Param("carNum")String carNum);


	/**
	 * @description: 根据车牌号15天内查询某车某天抓拍次数
	 * @author wcl
	 * @date 2020/4/14
	 */
	int queryCaptureCountByCarNum(@Param("carNum")String carNum,@Param("catTime")String catTime);

	/**
	 * @description: 根据车牌号查询某天的抓拍记录详情
	 * @author wcl
	 * @date 2020/4/14
	 */
	List<Map<String,Object>> queryCaptureDetailsByCarNum(@Param("params")Map<String,Object> params);

}