package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.CarGroupDetail;

import java.util.List;
import java.util.Map;

public interface CarGroupDetailService {

    Map<String,Object> selectCarGroupDetailList(Map<String, Object> params) throws OssRenderException;

    void insertCarGroupDetail(List carGroupDetails) throws OssRenderException;

    void updateCarGroupDetailById(CarGroupDetail carGroupDetail) throws OssRenderException;

    void deleteCarGroupDetailById(int id) throws OssRenderException;

}
