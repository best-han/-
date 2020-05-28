package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.CaptureDevice;

import java.util.List;
import java.util.Map;

public interface CaptureDeviceService {

    /*
     * 设备信息 新增 ygy
     * */
    void insertDevice(String communityCode, List devices) throws OssRenderException;

    /*
     * 设备信息删除 ygy
     * */
    void deleteByCode(String code) throws OssRenderException;

    /*
     * 设备信息更新 ygy
     * */
    void updateByCode(CaptureDevice captureDevice) throws OssRenderException;

    /*
     * 查询设备信息 列表 ygy
     * */
    Map<String, Object> selectDeviceList(Map<String, Object> params) throws OssRenderException;
}
