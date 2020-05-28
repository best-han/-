package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.feign.QueryImgByImgClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryImgByImgImpl {
    @Autowired
    private QueryImgByImgClient queryImgByImgClient;


    public Map<String, Object> queryPersonCodeByImg(Map<String, Object> params) throws OssRenderException {

        if (params.get("face_image") == null || StringUtils.isEmpty(params.get("face_image").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "image不能为空");
        }
        String personInfo = null;
        Map resultMap = new HashMap();
        try {

            personInfo = queryImgByImgClient.getPersonCodeByImg(params);
            String []resultParams=personInfo.split(";");
            String personCode1=resultParams[0];
            String personCode2=resultParams[1];
            String personCode3=resultParams[2];

//            拼接字符串 防止为空串
            if (personCode1.length()<=0){
                personCode1="-1";
            }
            if (personCode2.length()<=0){
                personCode2="-1";
            }
            if (personCode3.length()<=0){
                personCode3="-1";
            }

            String personCode=personCode1+","+personCode2+","+personCode3;

            resultMap.put("person_code",personCode);
            resultMap.put("type",resultParams[3]);

        } catch (OssRenderException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}



