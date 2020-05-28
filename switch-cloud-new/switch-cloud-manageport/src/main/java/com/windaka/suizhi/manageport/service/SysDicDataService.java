package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.SysDicData;

import java.util.List;
import java.util.Map;

public interface SysDicDataService {

    /*
     * 字典数值 插入 ygy
     * */
    void insertSysDicData(String dicCode, List sysDicDatas) throws OssRenderException;

    /*
     * 字典数值 删除 by id   ygy
     * */
    void deleteById(String id) throws OssRenderException;

    /*
     *字典数值 更新 by id  ygy
     * */
    void updateById(SysDicData sysDicData) throws OssRenderException;

    /*
     * 字典数值 查询 by keyword  ygy
     * */
    Map<String,Object> selectSysDicDataList(Map<String, Object> params) throws OssRenderException;

}
