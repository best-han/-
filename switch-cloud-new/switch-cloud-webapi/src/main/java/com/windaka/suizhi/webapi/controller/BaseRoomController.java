package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.AppUserService;
import com.windaka.suizhi.webapi.service.BaseRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wcl
 * @Date 2020/4/10 0010 下午 4:15
 */
@Slf4j
@RestController
public class BaseRoomController extends BaseController {
	@Autowired
	private BaseRoomService baseRoomService;
	@Autowired
	private AppUserService appUserService;

	/**
	 * @description: 地图-房屋楼栋列表
	 * @author wcl
	 * @date 2020/4/10
	 */
	@GetMapping("/building/list")
	public Map<String,Object> queryBuildingList(@RequestParam Map<String, Object> params){

		try{
			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}

			List<Map<String,Object>> list = baseRoomService.queryBuildingList(params);
			Map<String,Object> map = new HashMap<>();
			map.put("list",list);
			return render(map);
		}catch (Exception e){
			log.error("[BaseRoomController.queryBuildingList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 小区、楼和单元树状展示 hjt
	 * @param params
	 * @return
	 */
	@GetMapping("/house/cellByBuilding/list")
	public Map<String,Object> queryCellByBuilding(@RequestParam Map<String, Object> params){

		try{
			if (ObjectUtils.isEmpty(params.get("xqCode")) ) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}
			List<Map<String,Object>> list = baseRoomService.queryCellByBuilding(params);
			/*LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
			return render(stringRedisTemplate.boundHashOps("cellByBuildingList").get(loginAppUser.getUsername()));*/
			return render(list);
		}catch (Exception e){
			log.error("[BaseRoomController.queryCellByBuilding,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}
	/**
	 * 查询该小区下的楼或单元树状展示  hjt
	 * @param params
	 * @return
	 */
	@GetMapping("/house/buildingOrCell/list")
	public Map<String,Object> queryBuildingOrCellByXq(@RequestParam Map<String, Object> params){

		try{
			List<Map<String,Object>> list = baseRoomService.queryBuildingCellByXq(params);
			return render(list);
		}catch (Exception e){
			log.error("[BaseRoomController.queryBuildingOrCellByXq,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	/*@Autowired
	StringRedisTemplate stringRedisTemplate;
	*//**
	 * 小区、楼和单元树状展示提前查 hjt
	 * @param
	 * @return
	 *//*
	@PostMapping("/webapi-internal/preQueryCellByBuilding")
	public void preQueryCellByBuilding(@RequestBody LoginAppUser loginAppUser){

		try{
			Map<String,Object> params=new HashMap<>();
			String xqCodes="";
			String sysLevel = loginAppUser.getSysLevel();   //用户级别（1：超管，2：区级管理员，3：小区普通用户，4：街道管理员）
			if("3".equals(sysLevel)){//普通用户
				xqCodes = htUserXQDao.queryXQCodeByUserId(loginAppUser.getUserId());
			}
			if("1".equals(sysLevel)){//超管
				xqCodes = htUserXQDao.queryAllXQCode();
			}
			if("2".equals(sysLevel)){//区级管理员
				xqCodes=htUserXQDao.queryAllAreaXqCodeByUserId(loginAppUser.getUserId());
			}
			if("4".equals(sysLevel)){//街道级管理员
				xqCodes=htUserXQDao.queryAllSubdistrictXqCodeByUserId(loginAppUser.getUserId());
			}
			if (StringUtils.isBlank(xqCodes)) {
				throw new OssRenderException(ReturnConstants.CODE_FAILED, "该用户未关联查看小区");
			}
			params.put("xqCode", xqCodes);
			List<Map<String,Object>> list = baseRoomService.queryCellByBuilding(params);
			stringRedisTemplate.boundHashOps("cellByBuildingList").put(loginAppUser.getUsername(), JSON.toJSONString(list));
			//return render(list);
		}catch (Exception e){
			log.error("[BaseRoomController.preQueryCellByBuilding,参数：{},异常信息{}]",loginAppUser,e.getMessage());
			//return failRender(e);
		}
	}*/



	/**
	 * @description: 一房一档-房屋基础信息
	 * @author wcl
	 * @date 2020/4/14
	 */
	@GetMapping("/house/room/{roomCode}/info")
	public Map<String,Object> queryRoomInfoByRoomCode(@PathVariable("roomCode") String roomCode) {

		try {
			List<Map<String,Object>> list = baseRoomService.queryRoomInfoByRoomCode(roomCode);
			return render(list);
		} catch (Exception e) {
			log.error("[BaseRoomController.queryRoomInfoByRoomCode,参数：{},异常信息{}]", e.getMessage());
			return failRender(e);
		}
	}

		/**
		 * @description: 一房一档-人员信息列表
		 * @author wcl
		 * @date 2020/4/14
		 */
		@GetMapping("/house/room/{roomCode}/persons")
		public Map<String,Object> queryPersonInfoByRoomCode(@PathVariable("roomCode") String roomCode) {

			try {
				Map<String, Object> map = baseRoomService.queryPersonInfoByRoomCode(roomCode);
				return render(map);
			} catch (Exception e) {
				log.error("[BaseRoomController.queryPersonInfoByRoomCode,参数：{},异常信息{}]", e.getMessage());
				return failRender(e);
			}
		}


			/**
			 * @description: 一房一档-车信息列表
			 * @author wcl
			 * @date 2020/4/14
			 */
			@GetMapping("/house/room/{roomCode}/cars")
			public Map<String,Object> queryCarInfoByRoomCode(@PathVariable("roomCode") String roomCode){

				try{
					Map<String,Object> map = baseRoomService.queryCarInfoByRoomCode(roomCode);
					return render(map);
				}catch (Exception e){
					log.error("[BaseRoomController.queryCarInfoByRoomCode,参数：{},异常信息{}]",e.getMessage());
					return failRender(e);
				}
			}


	/**
	 * @description: 一房一档-犬类管理
	 * @author wcl
	 * @date 2020/4/15
	 */
	@GetMapping("/house/room/{roomCode}/dogs")
	public Map<String,Object> queryDogsByRoomCode(@PathVariable("roomCode") String roomCode){

		try{
			Map<String,Object> map = baseRoomService.queryDogsByRoomCode(roomCode);
			return render(map);
		}catch (Exception e){
			log.error("[BaseRoomController.queryDogsByRoomCode,参数：{},异常信息{}]",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * @description: 实有房产列表查询
	 * @author wcl
	 * @date 2020/4/15
	 */
	@GetMapping("/house/room/list")
	public Map<String,Object> queryRoomInfoList(@RequestParam Map<String, Object> params){

		try{
			if (params.get("xqCode") == ""|| ObjectUtil.isNull(params.get("xqCode"))) {
				//验证当前用户查询权限
				LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
				params.put("xqCode", appUserService.checkAuth(loginAppUser.getUserId()));
			}

			Map<String,Object> map = baseRoomService.queryRoomInfoList(params);
			return render(map);
		}catch (Exception e){
			log.error("[BaseRoomController.queryRoomInfoList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

	@GetMapping("/house/roomByBuilding/list")
	public Map<String,Object> houseRoomByBuildingList(@RequestParam Map<String, Object> params){

		try{
			Map<String,Object> map = baseRoomService.houseRoomByBuildingList(params);
			return render(map);
		}catch (Exception e){
			log.error("[BaseRoomController.houseRoomByBuildingList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

}
