package com.windaka.suizhi.manageport.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface FaceCriminalInfoService {
    void insertFaceCriminalInfo(List faceCriminalInfo) throws OssRenderException;

    void deleteFaceCriminalInfo(Map<String, Object> params) throws OssRenderException;
}
