package com.windaka.suizhi.manageport.service;



import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.FaceGroupDetail;

import java.util.List;
import java.util.Map;

public interface FaceGroupDetailService {

    /*
     * 布控库人员明细  新增 ygy
     * */
    void insertFaceGroupDetail(List faceGroupDetails) throws OssRenderException;
    /*
     * 布控库人员明细 删除 ygy
     * */
    void deleteById(String id) throws OssRenderException;
    /*
     * 布控库人员明细 更新 ygy
     * */
    void updateById(FaceGroupDetail faceGroupDetail) throws OssRenderException;
    /*
     * 布控库 人员明细 列表查询 ygy
     * */
    Map<String, Object> selectFaceGroupDetailList(Map<String, Object> params);
}
