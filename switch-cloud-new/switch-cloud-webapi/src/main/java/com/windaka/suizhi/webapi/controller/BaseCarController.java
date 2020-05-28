package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.dao.BaseCarDao;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.BaseCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * @Description 实有车辆模块
 * @Author wcl
 * @Date 2020/4/3
 */
@Slf4j
@RestController
public class BaseCarController extends BaseController {

	@Autowired
	private BaseCarService baseCarService;
	@Autowired
	private BaseCarDao baseCarDao;
	@Autowired
	private AppUserService appUserService;

	/**
	 * @description: 车辆列表查询
	 * @author wcl
	 * @date 2020/4/3
	 */
	@GetMapping("/car/list")
	public Map<String,Object> queryCarInfoList(@RequestParam Map<String, Object> params){

		try{
			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}

			Map<String,Object> map = baseCarService.queryCarInfoList(params);
			return render(map);
		}catch (Exception e){
			log.info("[BaseCarController.queryCarInfoList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 车辆品牌下拉框
	 * @author wcl
	 * @date 2020/4/13
	 */
	@GetMapping("/car/brandName")
	public Map<String,Object> queryCarBrandName(@RequestParam String brandNameLikeStr){

		try{
			List list = baseCarDao.queryCarBrandName(URLDecoder.decode(brandNameLikeStr, "UTF-8"));
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryCarBrandName,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}


	/**
	 * @description: 车辆类型下拉框
	 * @author wcl
	 * @date 2020/4/16
	 */
	@GetMapping("car/typeName")
	public Map<String,Object> queryCarTypeName(){

		try{
			List list = baseCarDao.queryCarTypeName();
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryCarTypeName,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 车辆颜色下拉框
	 * @author wcl
	 * @date 2020/5/7
	 */
	@GetMapping("car/colorName")
	public Map<String,Object> queryCarColorName(){

		try{
			List list = baseCarDao.queryCarColorName();
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryCarColorName,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}


	/**
	 * @description: 车辆基础信息
	 * @author wcl
	 * @date 2020/4/7
	 */
	@GetMapping("/car/baseInfo/new")
	public Map<String,Object> carBaseInfo(@RequestParam Map<String, Object> params){

		try{
			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map=baseCarService.carBaseInfo(params);

			return render(map);
		}catch (Exception e){
			log.info("[BaseCarController.carBaseInfo,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}


	/**
	 * @description: 一车一档-车和车主基础信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	@GetMapping("/car/personInfoAndOwnerInfo")
	public Map<String,Object> queryPersonInfoAndOwnerInfoByCarNum(@RequestParam String carNum){
		try{
			List<Map<String,Object>> list = baseCarService.queryPersonInfoAndOwnerInfoByCarNum(carNum);
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryPersonInfoAndOwnerInfoByCarNum,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 一车一档-异常行为标签
	 * @author wcl
	 * @date 2020/4/13
	 */
	@GetMapping("/car/abnormalType")
	public Map<String,Object> queryAbnormalTypeByCarNum(@RequestParam Map<String, Object> params){

		try{

			//验证当前用户查询权限
			LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
			String xqCode = appUserService.checkAuth(loginAppUser.getUserId());
			params.put("xqCode",xqCode);

			List<Map<String,Object>> list=baseCarService.queryAbnormalTypeByCarNum(params);
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryAbnormalTypeByCarNum,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 一车一档-房产信息
	 * @author wcl
	 * @date 2020/4/13
	 */
	@GetMapping("/car/roomInfo")
	public Map<String,Object> queryRoomInfoByRoomCode(@RequestParam String carNum){

		try{
			List<Map<String,Object>> list=baseCarService.queryRoomInfoByCarNum(carNum);
			return render(list);
		}catch (Exception e){
			log.info("[BaseCarController.queryRoomInfoByRoomCode,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 一车一档-车位信息
	 * @author wcl
	 * @date 2020/4/14
	 */
	@GetMapping("/car/parking")
	public Map<String,Object> queryParkingByCarNum(@RequestParam String carNum){

		try{
			Map<String,Object> map=baseCarService.queryParkingByCarNum(carNum);
			return render(map);
		}catch (Exception e){
			log.info("[BaseCarController.queryParkingByCarNum,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 一车一档-根据车牌号15天内查询某车某天抓拍次数
	 * @author wcl
	 * @date 2020/4/14
	 */
	@GetMapping("/car/captureTimes")
	public Map<String,Object> queryCaptureCountByCarNum(@RequestParam String carNum){

		try{
			Map<String,Object> map = baseCarService.queryCaptureCountByCarNum(carNum);
			return render(map);
		}catch (Exception e){
			log.info("[BaseCarController.queryCaptureCountByCarNum,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 一车一档-根据车牌号查询某天的抓拍记录详情
	 * @author wcl
	 * @date 2020/4/14
	 */
	@GetMapping("/car/capturDetails")
	public Map<String,Object> queryCaptureDetailsByCarNum(@RequestParam Map<String, Object> params){

		try{
			Map<String,Object> map = baseCarService.queryCaptureDetailsByCarNum(params);
			return render(map);
		}catch (Exception e){
			log.info("[BaseCarController.queryCaptureDetailsByCarNum,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}



}
