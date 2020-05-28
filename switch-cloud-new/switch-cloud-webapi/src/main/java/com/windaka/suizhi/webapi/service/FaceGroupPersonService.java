package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.Map;


public interface FaceGroupPersonService {
	/**
	 * 批量人脸-人脸类别维护
	 *人员布控-新增 ygy
	 * @param params
	 * @throws OssRenderException
	 */
	void saveFaceGroupPerson(Map<String, Object> params) throws OssRenderException;

	/**
	 * 人脸-人脸类别删除(同一小区可多个删除) hjt
     * 人员布控-删除 ygy
	 * @param params
	 * @throws OssRenderException
	 */
	void delFaceGroupPerson(Map<String, Object> params) throws OssRenderException;

    /**
     * 人员布控-列表查询 ygy
     * @param params
     * @return
     * @throws OssRenderException
     */
	Map<String,Object> faceFaceGroupList(Map<String, Object> params) throws OssRenderException;


}
