package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.manageport.dao.FaceStrangerInfoMapper;
import com.windaka.suizhi.manageport.model.FaceStrangerInfo;
import com.windaka.suizhi.manageport.service.FaceStrangerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FaceStrangerInfoServiceImpl implements FaceStrangerInfoService {

    @Autowired
    private FaceStrangerInfoMapper faceStrangerInfoMapper;

    @Override
    public void insertFaceStrangerInfo(List lists) {
        if(lists!=null) {
            Iterator i = lists.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                FaceStrangerInfo faceStrangerInfo= JSON.parseObject(JSON.toJSONString(t), FaceStrangerInfo.class);
                faceStrangerInfoMapper.insert(faceStrangerInfo);
            }
        }
    }
}
