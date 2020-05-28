package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
//import com.windaka.suizhi.webapi.model.FaceLibrary;

import java.util.Map;

public interface FaceGroupService {

    /**
     * 人脸类型维护
     * @throws OssRenderException
     */
    public void saveFaceGroup(Map<String, Object> params) throws OssRenderException;

    /**
     * 人脸类型修改
     * @param params
     * @throws OssRenderException
     */
    public void updateFaceGroup(Map<String, Object> params) throws OssRenderException;

    /**
     * 人脸类型删除
     * @param id
     * @throws OssRenderException
     */
    public void deleteFaceGroup(String id) throws OssRenderException;

    /**
     * 人脸类型查询
     * @param params
     * @throws OssRenderException
     */
    public Map<String,Object> queryFaceGroups(Map<String, Object> params) throws OssRenderException;

    //public FaceLibrary selectFaceGroup(Map<String, Object> params) throws OssRenderException;
}
