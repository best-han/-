package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface CaptureDeviceService {

	/**
	 * 根据areaId查询xqCode和xqName
	 *
	 * @param xqCode
	 */
	List<Map<String,Object>> queryCaptureListByxqCode(String xqCode) throws OssRenderException;

	/**
	 * 查询监控设备列表  hjt
	 *
	 * @param
	 */
	List<Map<String,Object>> queryCaptureDeviceList(Map<String, Object> params) throws OssRenderException;

	/**
	 * 设备（摄像机）播放地址  hjt
	 *
	 * @param
	 */
	Map<String,Object> queryCaptureFLVByGbCode(Map<String, Object> params) throws OssRenderException;


}
