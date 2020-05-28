package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BaseCommunity;

import java.util.List;
import java.util.Map;

public interface BaseCommunityService {

    /*
     *添加小区信息 ygy
     * */
    void insertCommunity(List BaseCommunity) throws OssRenderException;

    /*
     * 删除小区 by code  ygy
     * */
    void deleteByCode(String code) throws OssRenderException;

    /*
     * 更新小区 by code ygy
     * */
    void updateByCode(BaseCommunity baseCommunity) throws OssRenderException;

    /*
     * 查询小区列表 by keyword ygy
     * */
    Map<String,Object> selectCommunityList(Map<String, Object> params) throws OssRenderException;

    /**
     * 清空小区 dee
     * @param code
     * @throws OssRenderException
     */
    void emptyCommunity(String code) throws OssRenderException;

}
