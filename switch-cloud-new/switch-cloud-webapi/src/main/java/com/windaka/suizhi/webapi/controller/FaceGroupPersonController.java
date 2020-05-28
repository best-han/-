package com.windaka.suizhi.webapi.controller;

import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.FaceGroupPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FaceGroupPersonController extends BaseController {
	@Autowired
	private FaceGroupPersonService faceGroupPersonService;

	/**
	* @Description: 批量维护人脸-人脸类别
	* @Param: [params]
	* @return: java.util.Map<java.lang.String,java.lang.Object>
	* @Author: Ms.wcl
	* @Date: 2019/5/8 0008
	 * 人员布控-新增 ygy
	*/
	@PostMapping("/face/faceGroup")
	public Map<String,Object> saveFaceGroupPerson(@RequestBody Map<String,Object> params) {
		try{
			LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
			params.put("userId",loginAppUser.getUserId());
			faceGroupPersonService.saveFaceGroupPerson(params);
			return render();
		}catch (Exception e){
			log.info("[FaceGroupPersonController.saveFaceGroupPerson,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 人脸-人脸类别删除(同一小区可多个删除) hjt
	 * 人员布控-删除 ygy
	 * @param id
	 * @return
	 */
	@DeleteMapping("/face/faceGroup/{id}")
	public Map<String,Object> delFaceGroupPerson(@PathVariable("id") String id) {
		try{
			Map<String,Object> params=new HashMap<>();
			LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
			params.put("userId",loginAppUser.getUserId());
			params.put("id",id);
			faceGroupPersonService.delFaceGroupPerson(params);
			return render();
		}catch (Exception e){
			log.info("[FaceGroupPersonController.delFaceGroupPerson,参数：{},异常信息{}]","",e.getMessage());
			return failRender(e);
		}
	}

	/**
	 * 人员布控-列表查询 ygy
	 * @param params
	 * @return
	 */
	@GetMapping("/face/faceGroup/list")
	public Map<String,Object> faceFaceGroupList(@RequestParam Map<String,Object> params){
		try {
			Map<String,Object> map=faceGroupPersonService.faceFaceGroupList(params);
			return render(map);
		} catch (Exception e) {
			log.info("[FaceGroupPersonController.faceFaceGroupList,参数：{},异常信息{}]",params,e.getMessage());
			return failRender(e);
		}
	}

}
