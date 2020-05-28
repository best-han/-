package com.windaka.suizhi.manageport.service.impl;


import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.feign.UpdateImageFeatureClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateImageFeatureServiceImpl {

    @Autowired
    private UpdateImageFeatureClient updateImageFeatureClient;

    public void  insertImageFeature(Map<String,Object> params) throws OssRenderException{

        if (params.get("face_id") == null || StringUtils.isEmpty(params.get("face_id").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "face_id不能为空");
        }
        if (params.get("face_features") == null || StringUtils.isEmpty(params.get("face_features").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "face_features不能为空");
        }

        if (params.get("face_type") == null || StringUtils.isEmpty(params.get("face_type").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "face_type不能为空");
        }
        try{
            updateImageFeatureClient.insertImageFeature(params);
        }catch (Exception e){

            throw new OssRenderException(ReturnConstants.CODE_FAILED, "算法端新增人脸失败");
        }


    }

    public void  deleteImageFeature(Map<String,Object> params) throws OssRenderException{

        if (params.get("face_id") == null || StringUtils.isEmpty(params.get("face_id").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "face_id不能为空");
        }

        if (params.get("face_type") == null || StringUtils.isEmpty(params.get("face_type").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "face_type不能为空");
        }
        try{
            updateImageFeatureClient.deleteImageFeature(params);
        }catch (Exception e){
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "算法端删除d人脸失败");
        }

    }

}
