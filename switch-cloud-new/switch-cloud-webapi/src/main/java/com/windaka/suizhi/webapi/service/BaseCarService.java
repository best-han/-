package com.windaka.suizhi.webapi.service;


import com.windaka.suizhi.api.common.Page;
import io.micrometer.shaded.org.pcollections.PMap;

import java.util.List;
import java.util.Map;

/**
* @Description: 实有车辆模块
* @Author: Ms.wcl
* @Date: 2020/4/3
*/
public interface BaseCarService {

    /**
     * @description: 车辆列表查询
     * @author wcl
     * @Date 2020/4/3
     */
    Map<String,Object> queryCarInfoList(Map<String,Object> params);

	/**
	 * @description: 车辆基础信息
	 * @author wcl
	 * @date 2020/4/7
	 */
	Map<String,Object> carBaseInfo(Map<String,Object> params);


	/**
	 * @description: 根据车牌号查询车和车主基础信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryPersonInfoAndOwnerInfoByCarNum(String carNum);


	/**
	 * @description: 根据车牌号查询异常行为标签
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryAbnormalTypeByCarNum(Map<String, Object> params);

	/**
	 * @description: 根据车牌号查询房屋信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	List<Map<String,Object>> queryRoomInfoByCarNum(String carNum);

	/**
	 * @description: 根据车牌号查询停车位
	 * @author wcl
	 * @date 2020/4/14
	 */
	Map<String,Object> queryParkingByCarNum(String carNum);

	/**
	 * @description: 根据车牌号15天内查询某车某天抓拍次数
	 * @author wcl
	 * @date 2020/4/14
	 */
	Map<String,Object> queryCaptureCountByCarNum(String carNum);


	/**
	 * @description: 根据车牌号查询某天的抓拍记录详情
	 * @author wcl
	 * @date 2020/4/14
	 */
	Map<String,Object> queryCaptureDetailsByCarNum(Map<String,Object> params);

}
