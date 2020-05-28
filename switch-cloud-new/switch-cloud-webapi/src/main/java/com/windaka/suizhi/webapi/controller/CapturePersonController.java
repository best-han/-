package com.windaka.suizhi.webapi.controller;


import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.CapturePersonService;
import com.windaka.suizhi.webapi.service.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CapturePersonController extends BaseController {
    @Autowired
    private CapturePersonService capturePersonService;
    @Autowired
    private AppUserServiceImpl appUserService;

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午8:43
     * @description：  陌生人员列表
     */
    @GetMapping("/person/stranger/list")
    public Map<String, Object> personStrangerList(@RequestParam Map<String, Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.personStrangerList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CapturePersonController.personStrangerList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 人员抓拍列表查询
     *@author: zdq
     *@time: 4/7/20 9:32 AM
     *
     */
    @PostMapping("/person/capture/list")
    public Map<String, Object> personCaptureList(@RequestBody Map<String, Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
//            Map map=capturePersonService.personCaptureList(params);
            Map map=capturePersonService.personCaptureListByES(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CapturePersonController.personCaptureList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午9:22
     * @description：  人员抓拍-单人记录列表
     */
    @GetMapping("/person/capture/info")
    public Map<String, Object> singleCapPersonList(@RequestParam Map<String, Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
//            Map map=capturePersonService.singleCapPersonList(params);
            Map map=capturePersonService.singleCapPersonListByES(params);
            return render(map);
        } catch (Exception e) {
            log.error("[CapturePersonController.singleCapPersonList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/8 上午8:28
     * @description：  疑似新增人员列表
     */
    @GetMapping("/person/added/list")
    public Map<String, Object> personAddedList(@RequestParam Map<String, Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.personAddedList(params);
            return render(map);
        } catch (Exception e) {
            log.error("[CapturePersonController.singleCapPersonList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/11 下午6:11
     * @description：  抓拍人员【以图搜图】点击查询
     */
    @GetMapping("/person/capture/byClick")
    public Map<String, Object> personCaptureListById(@RequestParam Map<String,Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.personCaptureListById(params);
            return render(map);
        } catch (Exception e) {
            log.error("[CapturePersonController.personAddedList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/12 上午11:52
     * @description：  抓拍人员【以图搜图】图像查询
     */
    @PostMapping ("/person/capture/byImage")
    public Map<String, Object> personCaptureListByImage(@RequestBody Map<String,Object> params) {
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.personCaptureListByImg(params);
            return render(map);
        } catch (Exception e) {
            log.error("[CapturePersonController.personAddedList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人员-人员列表tab
     *@author: zdq
     *@time: 4/8/20 10:12 AM
     *
     */
    @GetMapping("/person/quit/list")
    public Map<String, Object> queryQuitPersonList(@RequestParam Map<String,Object> params){
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.queryQuitPersonList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BasePersonController.queryQuitPersonList,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人员-忽略某小区的某人
     *@author: zdq
     *@time: 4/11/20 9:39 PM
     *
     */
    @PostMapping("/person/quit/ignore")
    public Map<String, Object> ignoreQuitPerson(@RequestBody Map<String,Object> params){
        try {
            LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
            capturePersonService.ignoreQuitPerson(params,loginAppUser.getUsername());
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.ignoreQuitPerson,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人员-迁出某小区的某人
     *@author: zdq
     *@time: 4/12/20 3:37 PM
     *
     */
    @PostMapping("/person/quit/delete")
    public Map<String, Object> deleteQuitPerson(@RequestBody Map<String,Object> params){
        try {
            LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
            capturePersonService.deleteQuitPerson(params,loginAppUser.getUsername());
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.deleteQuitPerson,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人员-查询处理列表
     *@author: zdq
     *@time: 4/12/20 3:47 PM
     *
     */
    @GetMapping("/person/quit/handle/list")
    public Map<String, Object> queryQuitPersonHandleList(@RequestParam Map<String,Object> params){
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.queryQuitPersonHandleList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BasePersonController.queryQuitPersonHandleList,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人员-查询迁出列表
     *@author: zdq
     *@time: 4/12/20 3:47 PM
     *
     */
    @GetMapping("/person/quit/delete/list")
    public Map<String, Object> queryQuitPersonDeleteList(@RequestParam Map<String,Object> params){
        try {
            //验证当前用户权限
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                String [] xqCodeArr = appUserService.checkAuth(loginAppUser.getUserId()).split(",");
                params.put("xqCode", Arrays.asList(xqCodeArr));
            }else {
                String []xqCodeArr=params.get("xqCode").toString().split(",");
                params.put("xqCode",Arrays.asList(xqCodeArr));
            }
            Map map=capturePersonService.queryQuitPersonDeleteList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BasePersonController.queryQuitPersonDeleteList,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

}
