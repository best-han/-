package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.SuspectedService;
import com.windaka.suizhi.webapi.service.impl.AppUserServiceImpl;
import com.windaka.suizhi.webapi.task.PersonAddTask;
import com.windaka.suizhi.webapi.task.PersonQuitTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
public class SuspectedController extends BaseController {

    @Autowired
    private SuspectedService suspectedService;
    @Autowired
    private AppUserServiceImpl appUserService;
    @Autowired
    private PersonAddTask personAddTask;
    @Autowired
    private PersonQuitTask personQuitTask;
    /**
     *
     *@description: (警务通)疑似迁入人员/迁出人员/迁入车辆总数
     *@author: zdq
     *@time: 5/14/20 6:12 PM
     *
     */
    @GetMapping("/suspected/count")
    public Map<String, Object> suspectedCount(@RequestParam Map<String, Object> params)
    {
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
            Map map=suspectedService.getSuspectedCount(params);
            return render(map);
        } catch (Exception e) {
            log.info("[suspectedController.suspectedCount,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似新增人
     *@author: zdq
     *@time: 5/15/20 2:03 PM
     *
     */

    @PostMapping("/suspected/person/add")
    public Map<String,Object> suspectedPersonAdd(@RequestBody Map<String,Object> params)
    {
        try {
            personAddTask.executeInternal();
            return render();
        }
        catch (Exception e)
        {
            return failRender(e);
        }
    }

    /**
     *
     *@description: 疑似迁出人
     *@author: zdq
     *@time: 5/15/20 2:03 PM
     *
     */

    @PostMapping("/suspected/person/quit")
    public Map<String,Object> suspectedPersonQuit(@RequestBody Map<String,Object> params)
    {
        try {
            personQuitTask.executeInternal();
            return render();
        }
        catch (Exception e)
        {
            return failRender(e);
        }
    }
}
