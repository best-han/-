package com.windaka.suizhi.mpi.controller;

import com.alibaba.fastjson.JSONObject;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.mpi.service.WebsocketXqService;
import com.windaka.suizhi.mpi.websocket.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wcl
 * @Date 2019/5/9 0009 上午 10:18
 */
@Slf4j
@RestController
public class WebsocketController extends BaseController {
	@Autowired
	private WebsocketXqService xqService;
/*	@Autowired
	private FaceAlarmRecordService faceAlarmRecordService;*/

	/** 
	* @Description: 人脸报警记录上传
	* @Param: [params] 
	* @return: java.util.Map<java.lang.String,java.lang.Object> 
	* @Author: Ms.wcl
	* @Date: 2019/5/9 0009
	*/ 
/*	@PostMapping("/face/alarmRecord")
	public Map<String,Object> saveFaceTrackRecord(@RequestBody Map<String,Object> params) {
		try{
			faceAlarmRecordService.saveFaceTrackRecord((String) params.get("xqCode"), (List<Map<String, Object>>) params.get("list"));
			return render();
		}catch (Exception e){
			log.info("[FaceAlarmRecordController.saveFaceTrackRecord,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}

	*//**
	* @Description:  人脸报警记录信息处理
	* @Param: [params]
	* @return: java.util.Map<java.lang.String,java.lang.Object>
	* @Author: Ms.wcl
	* @Date: 2019/5/10 0010
	*//*
	@PutMapping("/face/alarmRecord")
	public Map<String,Object> updateFaceTrackRecord(@RequestBody Map<String,Object> params) {
		try{
			faceAlarmRecordService.updateFaceAlarmRecord(params);
			return render();
		}catch (Exception e){
			log.info("[FaceAlarmRecordController.updateFaceTrackRecord,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}

	*//**
	 * @Description: 人脸报警记录列表查询
	 * @Param: [params]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 * @Author: Ms.wcl
	 * @Date: 2019/5/9 0009
	 *//*
	@GetMapping("/face/alarmRecord/list")
	public Map<String,Object> queryFaceTrackRecordList(@RequestParam Map<String,Object> params) {
		try{
			Page<Map<String,Object>> page = faceAlarmRecordService.queryFaceTrackRecordList(params);
			Map<String,Object> map = new HashMap<>();
			map.put("list",page);
			return render(page);
		}catch (Exception e){
			log.info("[FaceAlarmRecordController.queryList,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}

	*//**
	* @Description: 根据alarmId查询报警记录
	* @Param: [alarmId] 
	* @return: java.util.Map<java.lang.String,java.lang.Object> 
	* @Author: Ms.wcl
	* @Date: 2019/5/10 0010
	*//*
	@GetMapping("/face/alarmRecord")
	public Map<String,Object> queryByAlarmId(@RequestParam String alarmId) {
		try{
			Map map = faceAlarmRecordService.queryByAlarmId(alarmId);
			return render(map);
		}catch (Exception e){
			log.info("[FaceAlarmRecordController.queryByAlarmId,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}*/

	@GetMapping("/wpi")
	public Map<String,Object> queryWebsocketXq(@RequestParam Map<String,Object> params) {
		try{
			if(!params.containsKey("areaId")){
				throw new OssRenderException(ReturnConstants.CODE_FAILED,"缺少areaId参数");
			}
			if(!params.containsKey("xqCode")){
				params.put("xqCode","");
				//throw new OssRenderException(ReturnConstants.CODE_FAILED,"缺少xqCode参数");
			}
			if(!params.containsKey("tokenId")||StringUtils.isBlank((String)params.get("tokenId"))){
				throw new OssRenderException(ReturnConstants.CODE_FAILED,"缺少tokenId参数");
			}
			if(!params.containsKey("userId")||StringUtils.isBlank((String)params.get("userId"))){//根据userId判断登录进来的用户权限
				throw new OssRenderException(ReturnConstants.CODE_FAILED,"缺少userId参数");
			}
			List<Map<String,Object>> list = xqService.queryListXqInfo((String) params.get("areaId"),(String) params.get("xqCode"),(String) params.get("userId"));
			SocketServer.websocketXq.put((String) params.get("tokenId"),list);
			return render();
		}catch (Exception e){
			log.info("[WebsocketController.queryWebsocketXq,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}

	}

	/**
	 *	前端长链接推送消息-内部调用 hjt
	 * @param msg
	 * @return
	 */
	@GetMapping("/mpi-anon/internal")
	public Map<String,Object> sendWebsocketMessage(@RequestParam String msg){
		try{
			//log.info("接受到的提示消息为："+msg);
			JSONObject contentJson = JSONObject.parseObject(msg);
			//json对象转Map
			Map<String,Object> contentMap = (Map<String,Object>)contentJson;
			for(Map.Entry<String, List<Map<String,Object>>> entry: SocketServer.websocketXq.entrySet()) {
				List<Map<String,Object>> xqList=entry.getValue();
				for(int i=0;i<xqList.size();i++){
					Map<String,Object> xq=xqList.get(i);
					StringBuffer strBuffer = new StringBuffer();
					for (Map.Entry<String, Object> entity : xq.entrySet()) {
						strBuffer.append(entity.getKey()+",");
//						  System.out.println("key= " + entity.getKey() + " and value= " + entity.getValue());
						  }
					if(((String)xq.get("xqCode")).equals((String) contentMap.get("xqCode"))){
						log.info("所有小区："+strBuffer.toString());
						log.info("传递小区："+ contentMap.get("xqCode"));
						log.info("发送消息为："+msg);
						SocketServer.sendMessage(msg,entry.getKey());
					}
				}
			}
			return render();
		}catch(Exception e){
			log.info("[WebsocketController.sendWebsocketMessage,参数：{},异常信息{}]",msg,e.getMessage());
			return failRender(e);
		}
	}



}
