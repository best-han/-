package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CaptureCarService {

    void insertCaptureCar(String communityCode, List cars) throws OssRenderException, IOException;

    void deleteById(String id) throws OssRenderException;

    Map<String,Object> selectCaptureCarList(Map<String, Object> params) throws OssRenderException;


}
