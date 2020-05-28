package com.windaka.suizhi.webapi.controller;

import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.dao.BaseRoomDao;
import com.windaka.suizhi.webapi.dao.BigDataDao;
import com.windaka.suizhi.webapi.dao.IdleRoomDao;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.BigDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 大数据分析接口  hjt
 */
@Slf4j
@RestController
@RequestMapping("/bigData")
public class BigDataController extends BaseController {
	
	@Autowired
	private BigDataService bigDataService;
	@Autowired
	AppUserService appUserService;
	/**
	 * 
	 * @Description: 频繁夜归人员
	 * @param: @param params
	 * @param: @return      
	 * @return: Map<String,Object>
	 */
	@GetMapping("/nightReturnPerson")
	public Map<String,Object> nightReturnPerson(@RequestParam Map<String, Object> params) {
		
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.getNightReturnPerson(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.nightReturnPerson,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 夜归人员记录
	 * @param params
	 * @return
	 */
	@GetMapping("/nightReturnPerson/one")
	public Map<String,Object> nightReturnPersonOne(@RequestParam Map<String, Object> params) {

		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.getNightReturnPersonOne(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.nightReturnPersonOne,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
	
	/**
	 * 
	 * @Description: 频繁夜归车辆
	 * @param: @param params
	 * @param: @return      
	 * @return: Map<String,Object>
	 */
	@GetMapping("/nightReturnCar")
	public Map<String,Object> nightReturnCar(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.getNightReturnCar(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.nightReturnCar,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 夜归车辆记录
	 * @param params
	 * @return
	 */
	@GetMapping("/nightReturnCar/one")
	public Map<String,Object> nightReturnCarOne(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.getNightReturnCarOne(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.nightReturnCarOne,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
	
	/**
	 * 
	 * @Description: 用水异常研判
	 * @param: @param params
	 * @param: @return      
	 * @return: Map<String,Object>
	 */
	@GetMapping("/waterRate")
	public Map<String,Object> waterRate(@RequestParam Map<String, Object> params) {
		try {
			if (ObjectUtils.isEmpty( params.get("xqCode") )) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.waterRate(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.waterRate,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
	
	/**
	 * 
	 * @Description: 群租房研判
	 * @param: @param params
	 * @param: @return      
	 * @return: Map<String,Object>
	 */
	@GetMapping("/groupResearch")
	public Map<String,Object> groupRentalResearch(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.groupRentalResearch(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.groupRentalResearch,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 日租房研判 hjt
	 * @param params
	 * @return
	 */
	@GetMapping("/dayRentalResearch")
	public Map<String,Object> dayRentalResearch(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.dayRentalResearch(params);
			return render(map);
		} catch (Exception e) {
			log.error("[CallRecordController.nightReturnCar,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 疑似闲置房屋列表 hjt
	 * @param params
	 * @return
	 */
	@GetMapping("/room/idle")
	public Map<String,Object> queryIdleRoomList(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.queryIdleRoomList(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.queryIdleRoomList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
	/**
	 * 空置房屋疑似居住列表 hjt
	 * @param params
	 * @return
	 */
	@GetMapping("/idleRoomLived")
	public Map<String,Object> queryIdleRoomLivedList(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("xqCode") == null || params.get("xqCode") == "") {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			Map<String,Object> map = bigDataService.queryIdleRoomLivedList(params);
			return render(map);
		} catch (Exception e) {
			log.error("[BigDataController.queryIdleRoomLivedList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
/**************************************************大数据插入接口******************************************************* **/
	//疑似闲置房屋添加
	@PostMapping("/idleRoomAdd")
	public Map<String,Object> idleRoomAddInterface() {
		try {
			this.idleRoomAdd();
			return render();
		} catch (Exception e) {
			log.error("[BigDataController.idleRoomAddInterface,异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}
	//增加闲置房屋疑似居住
	@PostMapping("/idleRoomLivedAdd")
	public Map<String,Object> idleRoomLivedAddInterface() {
		try {
			this.idleRoomLivedAdd();
			return render();
		} catch (Exception e) {
			log.error("[BigDataController.idleRoomLivedAddInterface,异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}
	//实有房产列表增加
	@PostMapping("/jBaseHouseAdd")
	public Map<String,Object> jBaseHouseAddInterface() {
		try {
			this.jBaseHouseAdd();
			return render();
		} catch (Exception e) {
			log.error("[BigDataController.jBaseHouseAddInterface,异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}
	//群租房添加
	@PostMapping("/groupRentalRoomAdd")
	public Map<String,Object> groupRentalRoomAddInterface() {
		try {
			this.groupRentalRoomAdd();
			return render();
		} catch (Exception e) {
			log.error("[BigDataController.groupRentalRoomAddInterface,异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	@Autowired
	IdleRoomDao idleRoomDao;
	/**
	 * 增加疑似闲置房屋  hjt
	 *
	 * @param
	 * @return
	 */
	public void idleRoomAdd() {
		log.info("****************执行idleRoomAdd开始*************");
		Map<String, Object> params = new HashMap<>();
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		date.setTime(c.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		params.put("useDate", sdf.format(date));

		params.put("minimum", 0);//用水最低阈值

		List<Map<String, Object>> list = idleRoomDao.queryIdleRoomByWaterUse(params);
		int idleMonth=0;
		for (Map map : list) {
			List<Map<String, Object>> idleRooms = idleRoomDao.queryIdleRoom(map);
			if (idleRooms != null && idleRooms.size() > 0) {
				idleMonth= Integer.parseInt(idleRooms.get(0).get("idleMonth").toString());
				map.put("idleMonth",idleMonth+1);//原基础增加一个月
				idleRoomDao.updateIdleRoomByRoomCode(map);
			}else{
				map.put("idleMonth",1);
				idleRoomDao.addIdleRoom(map);
			}
		}
		log.info("****************执行idleRoomAdd成功*************");
	}

	/**
	 * 增加闲置房屋疑似居住  hjt
	 *
	 * @param
	 * @return
	 */
	public void idleRoomLivedAdd() {
		log.info("****************执行idleRoomLivedAdd开始*************");
		Map<String, Object> params = new HashMap<>();
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		date.setTime(c.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		params.put("useDate", sdf.format(date));

		params.put("minimum", 0);//用水最低阈值

		List<Map<String, Object>> list = idleRoomDao.queryIdleRoomLivedByWaterUse(params);
		int liveMonth=0;
		for (Map map : list) {
			List<Map<String, Object>> idleRooms = idleRoomDao.queryIdleRoom(map);
			if (idleRooms != null && idleRooms.size() > 0) {
				liveMonth= Integer.parseInt(idleRooms.get(0).get("liveMonth").toString());
				map.put("liveMonth",liveMonth+1);//原基础增加一个月
				idleRoomDao.updateIdleRoomByRoomCode(map);
			}else{
				map.put("liveMonth",1);
				idleRoomDao.addIdleRoom(map);
			}
		}
		log.info("****************执行idleRoomLivedAdd成功*************");
	}
	/**
	 * 实有房产列表查询定时任务  hjt
	 *
	 * @param
	 * @return
	 */
	@Autowired
	BaseRoomDao baseRoomDao;
	public void jBaseHouseAdd() {
		log.info("****************执行jBaseHouseAdd开始*************");
		baseRoomDao.deletejBaseHouse();
		baseRoomDao.addjBaseHouse();
		log.info("****************执行jBaseHouseAdd成功*************");
	}
	@Autowired
	BigDataDao bigDataDao;

	/**
	 * 群租房添加  hjt
	 *
	 * @param
	 * @return
	 */
	public void groupRentalRoomAdd() {
		log.info("****************执行GroupRentalRoomAddTask开始*************");
		bigDataDao.deleteGroupRentalRoom();
		Map<String, Object> params = new HashMap<>();
		params.put("associateNum",5);
		bigDataDao.saveGroupRentalRoom(params);
		log.info("****************执行GroupRentalRoomAddTask成功*************");

	}

}
