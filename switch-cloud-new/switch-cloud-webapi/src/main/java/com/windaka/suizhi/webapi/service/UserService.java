package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.HtUserXq;
import com.windaka.suizhi.webapi.model.ext.ExtAlarmModel;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description 用户接口
 * @Author lixianhua
 * @Date 2020/4/26 8:26
 * @Version 1.0
 */
public interface UserService {

    // 根据人员ID获取下级ID
    List<HtUser> getUserListByUserId(String userId);

    // 根据人员ID获取小区集合
    List<HtUserXq> getUserXqByUserId(String userId);

}
