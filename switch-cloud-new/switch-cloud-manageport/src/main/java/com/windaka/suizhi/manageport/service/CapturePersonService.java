package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CapturePersonService {

    Map<String,Object> selectCapturePersonList(Map<String, Object> params) throws OssRenderException;

    void insertCapturePerson(String communityCode, List lists, List resultMapList) throws OssRenderException, IOException;

    void deleteCapturePersonById(int id) throws OssRenderException;

}
