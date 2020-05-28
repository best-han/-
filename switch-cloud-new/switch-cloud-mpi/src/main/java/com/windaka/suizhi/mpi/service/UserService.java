package com.windaka.suizhi.mpi.service;

import com.windaka.suizhi.mpi.model.BaseCommunity;
import com.windaka.suizhi.mpi.model.HtUser;
import com.windaka.suizhi.mpi.model.HtUserXq;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description 用户接口
 * @Author lixianhua
 * @Date 2020/5/6 15:41
 * @Version 1.0
 */
public interface UserService {

    // 根据userId获取上级用户集合
    List<HtUser> getUserListById(String userId);

    // 根据小区code获取用户
    List<HtUser>  getUserListByXqCode(String xqCode);

    // 获取用户集合
    List<HtUser> getUserList(HtUser condition);

    // 根据用户ID获取小区集合
    List<BaseCommunity> getXqByUserId(String userId);


}
