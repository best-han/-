package com.windaka.suizhi.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SubdistrictDao {

	/**
	 * 根据userId查询街道
	 * @param userId
	 * @return
	 */
	List<Map<String,Object>> querySubdistrictByUserId(@Param("userId") String userId);

	/**
	 * 绑定人员和街道关系
	 * @param params
	 * @return
	 */
	int saveUserSubdistrict(Map params);

	/**
	 * 删除该用户所有绑定的街道
	 * @param userId
	 * @return
	 */
	int deleteUserSubdistrict(String userId);

	/**
	 * 查询该区下的所有街道
	 * @param areaId
	 * @return
	 */
	List<Map<String,Object>> querySubdistrictByAreaId(String areaId);

	/**
	 * 查询该街道下所有小区
	 * @param areaId
	 * @return
	 */
	List<Map<String,Object>> queryXqBySubdistrictId(String areaId);

	/**
	 * 查询当前所有街道的区域
	 * @return
	 */
	List<Map<String,Object>> queryAllAreaBySubdistrictInfo();

	/**
	 * 查询当前登陆人所关联街道的区域信息
	 * @return
	 */
	List<Map<String,Object>> queryUserAreaBySubdistrict(String userId);


}
