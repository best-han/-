package com.windaka.suizhi.manageport.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.feign.ImageFeatureClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ImageFeatureServiceImpl {
    @Autowired
    private ImageFeatureClient imageFeatureClient;

    /**
     * @author ：ygy
     * @date ：2020/3/31 下午2:28
     * @description： 获取基础图像特征   参数 记录id 、 图像路径
     *
     * 返回 字符串    记录id+feature
     */
    public Map<String, Object> getBaseImageFeature(Map<String, Object> params) throws OssRenderException {

        if (params.get("record_id") == null || StringUtils.isEmpty(params.get("record_id").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "id不能为空");
        }
        if (params.get("face_image") == null || StringUtils.isEmpty(params.get("face_image").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "image不能为空");
        }

        String featureInfo = null;
        Map resultMap=new HashMap();
        try {

            featureInfo = imageFeatureClient.getBaseImageFeature(params);
            String []resultParams=featureInfo.split(";");
            resultMap.put("record_id",resultParams[0]);
            resultMap.put("face_id",resultParams[1]);
            resultMap.put("feature",resultParams[2]);
            resultMap.put("type",resultParams[3]);

        } catch (OssRenderException e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * @author ：ygy
     * @date ：2020/3/31 下午2:28
     * @description： 获取图像特征   参数 记录id 、 图像路径
     *
     * 返回 字符串    记录id+人脸id+feature+ type(0 正常人脸 1 陌生人脸 2 未识别人脸  3 异常)
     */
    public Map<String, Object> getCapImageFeature(Map<String, Object> params) throws OssRenderException {

        if (params.get("record_id") == null || StringUtils.isEmpty(params.get("record_id").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "id不能为空");
        }
        if (params.get("face_image") == null || StringUtils.isEmpty(params.get("face_image").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "image不能为空");
        }
        String featureInfo = null;
        Map resultMap=new HashMap();
        try {

            featureInfo = imageFeatureClient.getCapImageFeature(params);
            String []resultParams=featureInfo.split(";");
            resultMap.put("record_id",resultParams[0]);
            resultMap.put("face_id",resultParams[1]);
            resultMap.put("feature",resultParams[2]);
            resultMap.put("type",resultParams[3]);
            resultMap.put("sim",resultParams[4]);

        } catch (OssRenderException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
