package com.windaka.suizhi.mpi.service.impl;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.mpi.dao.CaptureCarMapper;
import com.windaka.suizhi.mpi.dao.MsgSocketIdDao;
import com.windaka.suizhi.mpi.service.CaptureCarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaptureCarServiceImpl implements CaptureCarService {
    @Autowired
    private CaptureCarMapper captureCarMapper;
    @Autowired
    private MsgSocketIdDao msgSocketIdDao;



    @Override
    public List<Map<String, Object>> queryCapCarList(Map<String, Object> params) throws OssRenderException {
        //        根据记录表查找 上一次查询的最大id
        int lastId = msgSocketIdDao.queryLastIdByRecordName("capture_car");

        //未发送的 告警人员列表
        List<Map<String, Object>> lists =captureCarMapper.queryCapCarList(lastId);
        if (lists.size() > 0) {

            for (Map<String,Object> t:lists){
                //处理图片 拼接地址
                if (t.get("capImage")!=null &&! StringUtils.isEmpty(t.get("capImage").toString())){

                    t.put("capImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("capImage"));

                }
                if(t.get("fullImage")!=null &&! StringUtils.isEmpty(t.get("fullImage").toString())){
                    t.put("fullImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("fullImage"));
                }
                if(t.get("carNum")!=null && !StringUtils.isEmpty(t.get("carNum").toString())){
                    if(t.get("carNum").equals("0"))
                    {
                        t.put("carNum","未识别");
                    }
                }
                else
                    t.put("carNum","未识别");
                t.put("webStatus","r");//推送标志
                t.put("msgType","2");
            }
//            Iterator i=lists.iterator();
//            while (i.hasNext()){
//                Map<String,Object> t=(Map<String,Object>)i.next();
//
//
//                //处理图片 拼接地址
//                if (t.get("capImage")!=null &&! StringUtils.isEmpty(t.get("capImage").toString())){
//
//                    t.put("capImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("capImage"));
//
//                }
//                if(t.get("fullImage")!=null &&! StringUtils.isEmpty(t.get("fullImage").toString())){
//                    t.put("fullImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("fullImage"));
//                }
//                t.put("webStatus","r");//推送标志
//                t.put("msgType","2");
//            }

//            更新id记录表
            Map<String, Object> lastRecord = lists.get(lists.size() - 1);//获取最后一条id (max)
            lastId = (Integer) lastRecord.get("id");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("recordName", "capture_car");
            innerParam.put("maxId", lastId);
            msgSocketIdDao.updateLastIdByRecordName(innerParam);
        }

        return lists;
    }

}
