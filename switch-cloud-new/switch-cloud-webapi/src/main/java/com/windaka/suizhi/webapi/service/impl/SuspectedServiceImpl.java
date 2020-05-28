package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.webapi.dao.BasePersonMapper;
import com.windaka.suizhi.webapi.dao.CapturePersonMapper;
import com.windaka.suizhi.webapi.service.SuspectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SuspectedServiceImpl implements SuspectedService {

    @Autowired
    private CapturePersonMapper capturePersonMapper;

    @Override
    public Map<String, Object> getSuspectedCount(Map<String, Object> params) {
        Map<String, Object> resultMap=new HashMap<>();
        int suspectedAddPersonCount=capturePersonMapper.selectPersonAddListCount(params);
        int suspectedQuitPersonCount=capturePersonMapper.queryPersonQuitTotal(params);
        int suspectedAddCarCount=0;
        resultMap.put("suspectedAddPersonCount",suspectedAddPersonCount);
        resultMap.put("suspectedQuitPersonCount",suspectedQuitPersonCount);
        resultMap.put("suspectedAddCarCount",suspectedAddCarCount);
        return resultMap;
    }
}
