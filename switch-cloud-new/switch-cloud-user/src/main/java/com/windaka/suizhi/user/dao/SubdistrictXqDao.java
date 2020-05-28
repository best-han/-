package com.windaka.suizhi.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SubdistrictXqDao {

	/**
	 * 根据SubdistrictId查询该街道下所有小区
	 * @param subdistrictId
	 * @return
	 */
	List<Map<String,Object>> queryXqBySubdistrictId(String subdistrictId);


}
