package com.windaka.suizhi.webapi.controller;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import com.windaka.suizhi.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户控制类
 * @Author lixianhua
 * @Date 2020/4/26 11:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/webUser")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 获取用户下级用户集合（包含自己）
     * @auther: lixianhua
     * @date: 2020/4/26 11:07
     * @param:
     * @return:
     */
    @GetMapping("/getListByUserId")
    public Map<String, Object> updateCar() {
        try {
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            List<HtUser> list = this.userService.getUserListByUserId(loginAppUser.getUserId());
            return render(list);
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }



}
