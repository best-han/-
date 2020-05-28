package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 车辆出入感知
 * @Author wcl
 * @Date 2020/4/10
 */
@Mapper
public interface CarInOutDao {

	/**
	 * @description: 车辆出入场统计列表查询
	 * @author wcl
	 * @date 2020/4/8
	 */
	List<Map<String,Object>> queryCarAccessList(@Param("params") Map<String,Object> params);

	/**
	 * @description:  车辆出入场统计条数(按权限)
	 * @author wcl
	 * @date 2020/4/8
	 */
	int carAccessTotalRows(@Param("params") Map<String, Object> params);

	/**
	 * @description: 查询某辆车的出入记录详情
	 * @author wcl
	 * @date 2020/4/10
	 */
	List<Map<String,Object>> queryCarAccessDetailsByCarNum(@Param("params") Map<String,Object> params);

	/**
	 * @description: 当天车辆高频出入次数>=3的
	 * @author wcl
	 * @date 2020/4/8
	 */
	List<Map<String,Object>> queryCarHighAccessList(@Param("params") Map<String,Object> params);

	/**
	 * @description: 根据personCode查询电话
	 * @author wcl
	 * @date 2020/4/9
	 */
	String queryPhoneByPersonCode(String persondCode);

	/**
	 * @description: 车牌号和时间查询最近一次抓拍照片
	 * @author wcl
	 * @date 2020/4/9
	 */
	String queryLastCarImage(@Param("carNum") String carNum,@Param("captureTime") String captureTime);

	/**
	 * @description: 查询房间信息,多个房产默认取第一个
	 * @author wcl
	 * @date 2020/4/9
	 */
	String queryRoomName(@Param("persondCode") String persondCode,@Param("purpose") String purpose,@Param("useNature") String useNature);


}
