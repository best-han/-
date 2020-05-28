package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.dao.CaptureCarMapper;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.CaptureCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wcl
 * @Date 2020/4/10 上午 10:53
 */
@Slf4j
@RestController
public class CaptureCarController extends BaseController {

	@Autowired
	private CaptureCarService captureCarService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private CaptureCarMapper captureCarMapper;


	/**
	 * @description: 车辆实时抓拍查询
	 * @author wcl
	 * @date 2020/4/10
	 */
	@GetMapping("/car/capture/query")
	public Map<String,Object> queryCarCaptureList(@RequestParam Map<String, Object> params){

		try{
//			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
//				//验证当前用户查询权限
//				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
//				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
//			}
//			Map<String,Object> map = captureCarService.queryCarCaptureList(params);

			//验证当前用户权限
			if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
				LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
				String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
				params.put("xqCode", Arrays.asList(xqCodeArr));
			}else {
				String []xqCodeArr=params.get("xqCode").toString().split(",");
				params.put("xqCode",Arrays.asList(xqCodeArr));
			}
			Map<String,Object> map = captureCarService.queryCarCaptureListByES(params);
			return render(map);
		}catch (Exception e){
			log.info("[CaptureCarController.queryCarCaptureList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 车牌模糊查询
	 * @author wcl
	 * @date 2020/5/7
	 */
	@GetMapping("/car/carNumLikeStr/query")
	public Map<String,Object> queryCarNumLikeStr(@RequestParam String carNumLikeStr){

		try{
			//验证当前用户查询权限
			LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
			String xqCode = appUserService.checkAuth(loginAppUser.getUserId());

			List<Map<String,Object>> list = captureCarMapper.queryCarNumLikeStr(carNumLikeStr,xqCode);
			return render(list);
		}catch (Exception e){
			log.info("[CaptureCarController.queryCarNumLikeStr,参数：{},异常信息{}]",carNumLikeStr,e.getMessage());
			return failRender(e);
		}
	}


}
