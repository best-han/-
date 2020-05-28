package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BaseParking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseParkingMapper {

    int insertBaseParking(BaseParking baseParking);

    int deleteBaseParking(String code);

    int updateBaseParking(BaseParking baseParking);

    /**
     *
     *@description: 查询停车位
     *@author: zdq
     *@time: 4/13/20 7:47 PM
     *
     */
    List<BaseParking> selectBaseParkingList(Map<String, Object> params);

}
