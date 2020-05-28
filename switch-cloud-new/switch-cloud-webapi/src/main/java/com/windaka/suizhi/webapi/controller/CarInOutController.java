package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.common.Page;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.CarInOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 车辆出入感知
 * @Author wcl
 * @Date 2020/4/10
 */
@Slf4j
@RestController
public class CarInOutController extends BaseController {

	@Autowired
	private CarInOutService carInOutService;
	@Autowired
	private AppUserService appUserService;

	/**
	 * @description: 车辆出入场统计列表查询
	 * @author wcl
	 * @date 2020/4/3
	 */
	@GetMapping("/tj/carAccess/list")
	public Map<String,Object> queryCarAccessList(@RequestParam Map<String, Object> params){

		try{
//			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
//				//验证当前用户查询权限
//				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
//				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
//			}
			//Page page = carInOutService.queryCarAccessList(params);
			//Map<String,Object> map = new HashMap<>();
			//map.put("list",page);
			//return render(page);
			//验证当前用户权限
			if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
				LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
				String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
				params.put("xqCode", Arrays.asList(xqCodeArr));
			}else {
				String []xqCodeArr=params.get("xqCode").toString().split(",");
				params.put("xqCode",Arrays.asList(xqCodeArr));
			}
			Map<String,Object> map = carInOutService.queryCarAccessListElasticSearch(params);
			return render(map);
		}catch (Exception e){
			log.info("[CarInOutController.queryCarAccessList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}


	/**
	 * @descriptio: 查询某辆车的出入记录详情
	 * @author wcl
	 * @date 2020/4/10
	 */
	@PostMapping("/car/highAccess/details")
	public Map<String,Object> queryCarAccessDetailsByCarNum(@RequestBody Map<String, Object> params){

		try{
//			Map<String,Object> map= carInOutService.queryCarAccessDetailsByCarNum(params);
			Map<String,Object> map=carInOutService.queryCarAccessDetailsByCarNumByES(params);
			return render(map);
		}catch (Exception e){
			log.info("[CarInOutController.queryCarAccessDetailsByCarNum,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}


	/**
	 * @description: 高频出入车辆
	 * @author wcl
	 * @date 2020/4/8
	 */
	@GetMapping("/car/sense/highAccess")
	public Map<String,Object> queryCarHighAccessList(@RequestParam Map<String, Object> params){

		try{
//			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
//				//验证当前用户查询权限
//				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
//				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
//			}
//			Map<String,Object> map = carInOutService.queryCarHighAccessList(params);
			if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
				LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
				String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
				params.put("xqCode", Arrays.asList(xqCodeArr));
			}else {
				String []xqCodeArr=params.get("xqCode").toString().split(",");
				params.put("xqCode",Arrays.asList(xqCodeArr));
			}
			Map<String,Object> map = carInOutService.queryCarHighAccessListElasticSearch(params);
			return render(map);
		}catch (Exception e){
			log.info("[CarInOutController.queryCarHighAccessList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
}
