package com.windaka.suizhi.webapi.service;

import cn.hutool.core.util.ObjectUtil;
import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.Map;

public interface PersonInoutService {
    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午2:05
     * @description：  人员出入记录
     */
    Map<String,Object> personAccessList(Map<String,Object> params) throws OssRenderException;
    Map<String,Object> personAccessListOptimize(Map<String,Object> params) throws OssRenderException;
    Map<String,Object> personAccessListElasticSearch(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/8 下午4:18
     * @description：  高频出入人员
     */
    Map<String,Object> personSenseHighAccess(Map<String, Object> params) throws OssRenderException;
    Map<String,Object> personSenseHighAccessElasticSearch(Map<String, Object> params) throws OssRenderException;
    Map<String,Object> singlePersonAccess(Map<String, Object> params) throws OssRenderException;
    Map<String,Object> singlePersonAccessByES(Map<String,Object> params) throws OssRenderException;
}
