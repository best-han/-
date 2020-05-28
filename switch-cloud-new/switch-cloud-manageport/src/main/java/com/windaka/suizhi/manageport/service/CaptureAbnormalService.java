package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.CaptureAbnormal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CaptureAbnormalService {

    /*
     * 异常行为 新增 ygy
     * */
    void insertCaptureAbnormal(String communityCode, List captureAbnormal) throws OssRenderException, IOException;

    /*
     * 异常行为 删除 ygy
     * */
    void deleteById(String id) throws OssRenderException;

    /*
     * 异常行为 更新 ygy
     * */
    void updateById(CaptureAbnormal captureAbnormal) throws OssRenderException;

    /*
     * 异常行为 列表查询 ygy
     * */
    Map<String, Object> selectCaptureAbnormalList(Map<String, Object> params) throws OssRenderException;
}
