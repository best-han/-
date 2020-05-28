package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.CaptureDeviceDao;
import com.windaka.suizhi.webapi.service.CaptureDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * @Description 摄像机设备
 * @Author wcl
 * @Date 2019/7/16 0016 下午 2:23
 */
@Slf4j
@Service
public class CaptureDeviceServiceImpl implements CaptureDeviceService {
	@Autowired
	private CaptureDeviceDao captureDeviceDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Map<String, Object>> queryCaptureListByxqCode(String xqCode) throws OssRenderException {
		List<Map<String,Object>> list = captureDeviceDao.queryCaptureListByxqCode(xqCode);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryCaptureDeviceList(Map<String,Object> params) throws OssRenderException {
		List<Map<String,Object>> list = captureDeviceDao.queryCaptureDeviceList(params);
		return list;
	}

	@Override
	public Map<String, Object> queryCaptureFLVByGbCode(Map<String,Object> params) throws OssRenderException {
		if(ObjectUtils.isEmpty(params.get("gbCode"))){
			throw new OssRenderException(ReturnConstants.CODE_FAILED,"gbCode为空");
		}
		String gbCode=params.get("gbCode").toString();
		if(ObjectUtils.isEmpty(params.get("gbCodeseq"))){
			throw new OssRenderException(ReturnConstants.CODE_FAILED,"gbCodeseq为空");
		}
		String gbCodeseq=params.get("gbCodeseq").toString();
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params1= new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params1, headers);*/
/*		ResponseEntity<String> response = restTemplate.getForEntity(PropertiesUtil.CAPTURE_DEVICE_URI+"api/v1/device/list", String.class);
		String responseBody = response.getBody();
		JSONObject contentJson = JSONObject.parseObject(responseBody);
		Map<String,Object> contentMap=(Map<String,Object>) contentJson;
		List<Map<String,Object>> deviceList=(List<Map<String,Object>>) contentMap.get("DeviceList");

		String serial=null;
		for(Map map:deviceList){
			if(deviceName.equals(map.get("Name"))){
				serial= map.get("ID").toString();
				break;
			}
		}
		if(StringUtils.isBlank(serial)){
			throw new OssRenderException(ReturnConstants.CODE_FAILED,"无对应的设备serial");
		}*/
		Map<String, String> params1= new HashMap<>();
		params1.put("serial",gbCode);
		params1.put("channel",gbCodeseq);
		Map<String,Object> contentMap = restTemplate.getForObject(PropertiesUtil.CAPTURE_DEVICE_URI+"api/v1/stream/start?serial={serial}&channel={channel}", Map.class,params1);
		/*responseBody = response.getBody();
		contentJson = JSONObject.parseObject(responseBody);
		contentMap=(Map<String,Object>) contentJson;*/
		String FLV=contentMap.get("FLV").toString();
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("FLV",FLV);
		return resultMap;
	}


}
