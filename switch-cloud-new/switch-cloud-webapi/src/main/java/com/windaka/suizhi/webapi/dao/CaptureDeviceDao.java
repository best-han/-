package com.windaka.suizhi.webapi.dao;

import com.windaka.suizhi.webapi.model.CaptureDevice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @Description: 摄像机设备
* @Param:  
* @return:  
* @Author: Ms.wcl
* @Date: 2019/7/16 0016
*/
@Mapper
public interface CaptureDeviceDao {

	/**
	 * 根据captureId查询数据
	 * @param captureId
	 * @return
	 */
	@Select("SELECT count(*) FROM zs_capture_info WHERE capture_id = #{captureId}")
	int queryByCaptureId(@Param("captureId") String captureId);

	/**
	 * 根据xqCode查询摄像机设备列表
	 * @param xqCode
	 * @return
	 */
	@Select("select c.manage_id as captureId,c.device_addr_name as deviceAddr,c.device_name as deviceName,c.device_location as deviceLocation,c.dchnl_device_code as capDevCode,c.dchnl_device_channel as capDevChannel,c.status,c.dchnl_rtsp as dchnlRtsp from face_capture_device c WHERE c.xq_code in (${xqCode})")
	List<Map<String,Object>> queryCaptureListByxqCode(@Param("xqCode") String xqCode);

	/**
	 * 查询摄像机设备列表  hjt
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryCaptureDeviceList(Map params);

}
