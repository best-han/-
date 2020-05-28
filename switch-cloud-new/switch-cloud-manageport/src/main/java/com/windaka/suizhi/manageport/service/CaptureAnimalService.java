package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CaptureAnimalService {

    /*
     * 宠物抓拍 新增 ygy
     * */
    void insertCapturePets(String communityCode, List capturePets) throws OssRenderException, IOException;

    /*
     * 宠物抓拍删除 ygy
     * */
    void deleteById(String id) throws OssRenderException;

    /*
     * 宠物抓拍列表查询 ygy
     * */
    Map<String, Object> selectCapturePetsList(Map<String, Object> params);

}
