package com.windaka.suizhi.webapi.controller;

import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.CaptureDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 摄像机设备
 * @Author hjt
 * @Date 2019/7/16 0016 下午 2:23
 */
@Slf4j
@RestController
public class CaptureDeviceController extends BaseController {

	@Autowired
	private CaptureDeviceService captureDeviceService;

	@Autowired
	protected AppUserService appUserService;

	/**
	 * 街道端-地图-设备（摄像机）列表
	 * @param
	 * @return
	 */
	@GetMapping("/capture")
	public Map<String,Object> queryCaptureListByXqCode(@RequestParam Map<String, Object> params) {
		try{
			if (params.get("xqCode")== null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			List<Map<String,Object>> captureInfoList= captureDeviceService.queryCaptureDeviceList(params);
			return render(captureInfoList);
		}catch (Exception e){
			log.info("[CaptureController.queryCaptureListByXqCode,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 设备（摄像机）播放地址  hjt
	 * @param
	 * @return
	 */
	@GetMapping("/capture/play")
	public Map<String,Object> queryCaptureFLVByGbCode(@RequestParam Map<String, Object> params) {
		try{
			Map<String,Object> map= captureDeviceService.queryCaptureFLVByGbCode(params);
			return render(map);
		}catch (Exception e){
			log.info("[CaptureController.queryCaptureFLVByGbCode,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
}
