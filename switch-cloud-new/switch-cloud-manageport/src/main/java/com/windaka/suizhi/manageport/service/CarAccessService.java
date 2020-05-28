package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CarAccessService {

    /*
     * 抓拍车辆出入 新增 ygy
     * */
    void insertCarAccess(String communityCode, List carAccesses) throws OssRenderException, IOException;


    /*
     * 抓拍车辆出入 删除 ygy
     * */
    void deleteById(String id) throws OssRenderException;


    /*
     * 抓拍车辆 列表查询 ygy
     * */
    Map<String, Object> selectCarAccessList(Map<String, Object> params) throws OssRenderException;
}
