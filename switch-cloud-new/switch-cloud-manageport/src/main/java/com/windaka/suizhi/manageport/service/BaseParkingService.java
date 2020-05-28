package com.windaka.suizhi.manageport.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BaseParking;

import java.util.List;

public interface BaseParkingService {

    /**
     *
     *@description: 添加停车位
     *@author: zdq
     *@time: 4/13/20 7:30 PM
     *
     */
    void insertBaseParking(String communityCode, List list) throws OssRenderException;

    /**
     *
     *@description: 修改停车位
     *@author: zdq
     *@time: 4/13/20 7:31 PM
     *
     */
    void updateBaseParking(BaseParking baseParking) throws OssRenderException;

    /**
     *
     *@description: 删除停车位
     *@author: zdq
     *@time: 4/13/20 7:32 PM
     *
     */
    void deleteBaseParking(String code) throws OssRenderException;
}
