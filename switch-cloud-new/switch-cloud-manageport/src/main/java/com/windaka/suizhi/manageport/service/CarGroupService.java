package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.CarGroup;

import java.util.List;
import java.util.Map;

public interface CarGroupService {

    Map<String,Object> selectCarGroupList(Map<String, Object> params) throws OssRenderException;

    void insertCarGroup(List carGroupList) throws OssRenderException;

    void updateCarGroupByCode(CarGroup carGroup) throws OssRenderException;

    void deleteCarGroupByCode(String code) throws OssRenderException;

}
