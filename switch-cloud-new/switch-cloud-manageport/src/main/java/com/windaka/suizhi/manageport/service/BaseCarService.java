package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BaseCar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BaseCarService {
    /*
     * 车辆信息 新增 ygy
     * */
    void insertCar(String num, List cars) throws OssRenderException, IOException;

    /*
     * 车辆信息 ygy
     * */
    void deleteByNum(String num) throws OssRenderException;

    /*
     * 车辆信息 ygy
     * */
    void updateByNum(BaseCar baseCar) throws OssRenderException, IOException;

    /*
     * 查询车辆信息 列表 ygy
     * */
    Map<String, Object> selectCarList(Map<String, Object> params) throws OssRenderException;
}
