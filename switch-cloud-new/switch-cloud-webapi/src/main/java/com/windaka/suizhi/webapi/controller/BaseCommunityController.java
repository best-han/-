package com.windaka.suizhi.webapi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.api.common.Page;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.service.BaseCommunityService;
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
public class BaseCommunityController extends BaseController {
    @Autowired
    private BaseCommunityService baseCommunityService;
    @Autowired
    private AppUserServiceImpl appUserService;

    /**
     * @author ：ygy
     * @date   ：2020/4/9 下午3:15
     * @description：  小区列表
     */
    @GetMapping("/xq/list")
    public Map<String,Object> xqList(@RequestParam Map<String, Object> params){
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
            Map resultMap=baseCommunityService.xqList(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.personBaseInfo,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
