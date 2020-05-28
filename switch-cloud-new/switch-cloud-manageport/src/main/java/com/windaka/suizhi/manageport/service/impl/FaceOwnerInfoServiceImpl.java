package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.manageport.dao.FaceOwnerInfoMapper;
import com.windaka.suizhi.manageport.model.FaceOwnerInfo;
import com.windaka.suizhi.manageport.service.FaceOwnerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FaceOwnerInfoServiceImpl implements FaceOwnerInfoService {

    @Autowired
    private FaceOwnerInfoMapper faceOwnerInfoMapper;

    @Override
    public void insertFaceOwnerInfo(List list) {
        if(list!=null)
        {
            Iterator i=list.iterator();
            while (i.hasNext()){
                Map<String, Object> t = (Map<String, Object>) i.next();
                FaceOwnerInfo faceOwnerInfo= JSON.parseObject(JSON.toJSONString(t), FaceOwnerInfo.class);
                faceOwnerInfoMapper.insert(faceOwnerInfo);
            }
        }
    }

    @Override
    public void deleteFaceOwnerInfo(Map<String, Object> params) {
        faceOwnerInfoMapper.delete(params);
    }
}
