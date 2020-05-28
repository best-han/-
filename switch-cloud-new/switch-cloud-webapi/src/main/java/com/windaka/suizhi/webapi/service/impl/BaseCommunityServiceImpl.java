package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.dao.BaseCommunityMapper;
import com.windaka.suizhi.webapi.service.BaseCommunityService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseCommunityServiceImpl implements BaseCommunityService {
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Override
    public Map<String, Object> xqList(Map<String, Object> params) throws OssRenderException {

        Map<String,Object> resultMap= new HashMap<>();
        List<Map<String,Object>> lists=baseCommunityMapper.communityList(params);

        resultMap.put("list",lists);

        return resultMap;
    }
}
