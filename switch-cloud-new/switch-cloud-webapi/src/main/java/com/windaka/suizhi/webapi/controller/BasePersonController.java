package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.BasePersonService;
import com.windaka.suizhi.webapi.service.CapturePersonService;
import com.windaka.suizhi.webapi.service.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class BasePersonController  extends BaseController {
    @Autowired
    private BasePersonService basePersonService;
    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private CapturePersonService capturePersonService;

///**
// * @author ：ygy
// * @date   ：2020/4/2 下午2:07
// * @description：  人员基础信息
// */
//    @GetMapping("/person/baseInfo")
//    public Map<String, Object> personBaseInfo(@RequestParam Map<String, Object> params) {
//        try {
//            //验证当前用户权限
//            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
//                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
//                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
//            }
//            Map resultMap=basePersonService.personBaseInfo(params);
//            return render(resultMap);
//        } catch (Exception e) {
//            log.info("[BasePersonController.personBaseInfo,参数：{},异常信息{}]", params, e.getMessage());
//            return failRender(e);
//        }
//    }

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:20
     * @description： 基础要素
     */
    @GetMapping("/baseData")
    public Map<String, Object> baseData(@RequestParam Map<String, Object> params) {
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

            Map resultMap=basePersonService.baseData(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.personBaseInfo,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }


//    /**
//     * @author ：ygy
//     * @date   ：2020/4/2 下午4:35
//     * @description：  人员分布信息
//     */
//    @GetMapping("/person/num")
//    public Map<String, Object> personDistributre(@RequestParam Map<String, Object> params) {
//        try {
//            //验证当前用户权限
//            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
//                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
//                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
//            }
//            List lists=basePersonService.personDistribute(params);
//            return render(lists);
//        } catch (Exception e) {
//            log.info("[BasePersonController.personDistributre,参数：{},异常信息{}]", params, e.getMessage());
//            return failRender(e);
//        }
//    }

    /**
     * @author ：ygy
     * @date   ：2020/4/3 下午4:12
     * @description：  现有人员列表
     */
    @GetMapping("/person/owner/list")
    public Map<String, Object> personList(@RequestParam Map<String, Object> params) {
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

            Map map=basePersonService.personList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BasePersonController.personList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息
     *@author: zdq
     *@time: 4/3/20 3:58 PM
     *
     */

    @GetMapping("/person/{personId}/info")
    public Map<String, Object> querySinglePerson(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            Map resultMap=basePersonService.querySinglePerson(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.querySinglePerson,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息-布控库标签
     *@author: zdq
     *@time: 4/3/20 3:58 PM
     *
     */

    @GetMapping("/person/{personId}/faceType")
    public Map<String, Object> queryFaceGroupLabel(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            List resultList=basePersonService.queryFaceGroupLabel(params);
            return render(resultList);
        } catch (Exception e) {
            log.info("[BasePersonController.queryFaceGroupLabel,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息-异常行为标签
     *@author: zdq
     *@time: 4/3/20 3:59 PM
     *
     */

    @GetMapping("/person/{personId}/abnormalType")
    public Map<String, Object> queryAbnormalLabel(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
            if (params.get("communityCode")=="" || ObjectUtil.isNull(params.get("communityCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("communityCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            List resultList=basePersonService.queryAbnormalLabel(params);
            return render(resultList);
        } catch (Exception e) {
            log.info("[BasePersonController.queryAbnormalLabel,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息-房产信息
     *@author: zdq
     *@time: 4/3/20 4:55 PM
     *
     */

    @GetMapping("/person/{personId}/house")
    public Map<String, Object> queryHouseListByPersonCode(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            Map resultMap=basePersonService.queryHouseListByPersonCode(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.queryHouseListByPersonCode,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息-车辆信息
     *@author: zdq
     *@time: 4/3/20 7:06 PM
     *
     */

    @GetMapping("/person/{personId}/car")
    public Map<String, Object> queryCarListByPersonCode(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            List resultList=basePersonService.queryCarListByPersonCode(params);
            return render(resultList);
        } catch (Exception e) {
            log.info("[BasePersonController.queryCarListByPersonCode,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 查询个人信息-本月抓拍次数变化
     *@author: zdq
     *@time: 4/3/20 7:17 PM
     *
     */

    @GetMapping("/person/{personId}/capture")
    public Map<String, Object> queryCaptureListByPersonCode(@PathVariable("personId") String personId,@RequestParam Map<String, Object> params){
        try {
            //验证当前用户权限
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            Map resultMap=basePersonService.queryCaptureTimesByPersonCode(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.queryCaptureListByPersonCode,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/15 上午11:33
     * @description：  查询个人 近15天抓拍记录
     */
    @GetMapping("/person/{personId}/capture15")
    public Map<String, Object> capPerson15Day(@PathVariable("personId") String personId,@RequestParam Map<String, Object> params){
        try {
            //验证当前用户权限
            params.put("personId",personId);
            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
            }
            Map resultMap=capturePersonService.capPerson15Day(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.queryCaptureListByPersonCode,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }



    @GetMapping("/person/{personId}/relation")
    public Map<String, Object> queryRelationShipByPersonCode(@PathVariable("personId") String personId){
        try {
            //验证当前用户权限
            Map<String, Object> params=new HashMap<>();
            params.put("personId",personId);
//            if (params.get("xqCode")=="" || ObjectUtil.isNull(params.get("xqCode"))){
//                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
//                params.put("xqCode",appUserService.checkAuth(loginAppUser.getUserId()));
//            }
            Map resultMap=basePersonService.queryRelationShipByPersonCode(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.queryRelationShipByPersonCode,参数：{},异常信息{}]", personId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午10:08
     * @description：  国家列表
     */
    @GetMapping("/country/list")
    public Map<String, Object> countryList(@RequestParam Map<String,Object> params){
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
            List list=basePersonService.countryList(params);
            return render(list);
        } catch (Exception e) {
            log.info("[BasePersonController.countryList,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }

}
