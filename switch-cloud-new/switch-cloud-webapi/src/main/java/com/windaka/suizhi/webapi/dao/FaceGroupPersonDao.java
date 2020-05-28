package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface FaceGroupPersonDao {
	/**
	 * 人脸-人脸类别维护
	 * @param params
	 * @return
	 * 人员布控-新增 ygy
	 */
	int saveFaceGroupPerson(Map<String, Object> params);

	/**
	 * 查询所有人脸类型
	 * @return
	 */
	List<Map<String,Object>> queryFaceGroups();

	/**
	 *  人脸-人脸类别删除(同一小区可多个删除) hjt
	 *  人员布控-删除 ygy
	 * @param params
	 */
	int delFaceGroupPerson(Map<String, Object> params);
	int deletFaceGroupPerson(Map<String, Object> params);

	/**
	 * 查询某种人脸类型的人的数量
	 * @param params
	 * @return
	 */
	int queryFaceGroupPersonNum(Map<String, Object> params);

	/**
	 * 人员布控-列表查询 ygy
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> faceFaceGroupList(Map<String, Object> params);
//	int faceFaceGroupListTotal(Map<String, Object> params);//后台分页 弃用
//	List<Map<String,Object>> crimeFaceGroupList(Map<String, Object> params);//犯罪人员列表

	/**
	 * 获取最大ID值
	 * @return
	 */
	int getMaxRecordId();

	void deleteFaceGroupPersonByCode(String id);

}
