package com.windaka.suizhi.webapi.controller;


import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.PersonInoutService;
import com.windaka.suizhi.webapi.service.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
public class PersonInoutController extends BaseController {
    @Autowired
    private PersonInoutService personInoutService;
    @Autowired
    private AppUserServiceImpl appUserService;

    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午2:49
     * @description：  人员出入记录
     */
    @GetMapping("/person/access")
    public Map<String, Object> personAccessList(@RequestParam Map<String, Object> params) {
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
//            Map map=personInoutService.personAccessListOptimize(params);
//            Map map=personInoutService.personAccessList(params);
            Map map=personInoutService.personAccessListElasticSearch(params);
            return render(map);
        } catch (Exception e) {
            log.info("[personInoutController.personAccessList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午4:24
     * @description：  高频出入人员列表
     */
    @GetMapping("/person/sense/highAccess")
    public Map<String, Object> personSenseHighAccess(@RequestParam Map<String, Object> params) {
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
            //Map map=personInoutService.personSenseHighAccess(params);
            Map map=personInoutService.personSenseHighAccessElasticSearch(params);
            return render(map);
        } catch (Exception e) {
            log.info("[personInoutController.personSenseHighAccess,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午4:45
     * @description：  高频出入人员-单人记录列表
     */
    @GetMapping("/person/sense/highAccess/info")
    public Map<String, Object> singlePersonAccessByCode(@RequestParam Map<String, Object> params) {
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
//            Map map=personInoutService.singlePersonAccess(params);
            Map map=personInoutService.singlePersonAccessByES(params);
            return render(map);
        } catch (Exception e) {
            log.info("[personInoutController.personSenseHighAccess,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
