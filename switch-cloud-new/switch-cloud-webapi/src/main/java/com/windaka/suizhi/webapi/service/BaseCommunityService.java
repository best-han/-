package com.windaka.suizhi.webapi.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.Map;

public interface BaseCommunityService {
    /**
     * @author ：ygy
     * @date   ：2020/4/9 下午3:07
     * @description：  xq 列表
     */
    Map<String,Object> xqList(Map<String,Object> params) throws OssRenderException;
}
