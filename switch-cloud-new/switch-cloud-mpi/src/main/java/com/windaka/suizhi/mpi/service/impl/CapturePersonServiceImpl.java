package com.windaka.suizhi.mpi.service.impl;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.mpi.dao.MsgSocketIdDao;
import com.windaka.suizhi.mpi.dao.auto.CapturePersonMapper;
import com.windaka.suizhi.mpi.service.CapturePersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CapturePersonServiceImpl implements CapturePersonService {
    @Autowired
    private CapturePersonMapper capturePersonMapper;
    @Autowired
    private MsgSocketIdDao msgSocketIdDao;

    @Override
    public List<Map<String, Object>> queryCapPersonList(Map<String, Object> params) throws OssRenderException {
        //        根据记录表查找 上一次查询的最大id
        int lastId = msgSocketIdDao.queryLastIdByRecordName("capture_person");

        //未发送的 抓拍人员列表
        List<Map<String, Object>> lists = capturePersonMapper.queryCapPersonList(lastId);
        if (lists.size() > 0) {

            Iterator i = lists.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();


                //处理图片 拼接地址
                if (t.get("faceImage") != null && !StringUtils.isEmpty(t.get("faceImage").toString())) {

                    t.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + t.get("faceImage"));

                }
                if (t.get("bodyImage") != null && !StringUtils.isEmpty(t.get("bodyImage").toString())) {

                    t.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + t.get("bodyImage"));

                }
                if (null == t.get("name") || StringUtils.isEmpty(t.get("name").toString())) {

                    t.put("name", "陌生人");

                }
                t.put("webStatus", "r");//推送标志
                t.put("msgType", "1");
            }

//            更新id记录表
            Map<String, Object> lastRecord = lists.get(lists.size() - 1);//获取最后一条id (max)
            lastId = (Integer) lastRecord.get("id");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("recordName", "capture_person");
            innerParam.put("maxId", lastId);
            msgSocketIdDao.updateLastIdByRecordName(innerParam);
        }

        return lists;
    }
}