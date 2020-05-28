package com.windaka.suizhi.manageport.service;



import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.SysDic;

import java.util.List;
import java.util.Map;


public interface SysDicService {

    /*
    * 字典 插入 ygy
    * */
    void insertSysDic(List sysDics) throws OssRenderException;

    /*
    * 字典 删除 by code   ygy
    * */
    void deleteByCode(String code) throws OssRenderException;

    /*
    *字典 更新 by code  ygy
    * */
    void updateByCode(SysDic sysDic) throws OssRenderException;

    /*
    * 字典 查询 by keyword  ygy
    * */
    Map<String,Object> selectSysDicList(Map<String, Object> params) throws OssRenderException;
}
