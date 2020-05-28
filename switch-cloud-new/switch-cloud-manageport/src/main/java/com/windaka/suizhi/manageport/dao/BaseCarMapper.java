package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BaseCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseCarMapper {

    int insertCar(BaseCar baseCar);

    int deleteByNum(String num);

    int updateByNum(BaseCar baseCar);

    List<BaseCar> selectCarList(Map<String, Object> params);

    int updateCarByCommunityCode(Map<String,Object> params);
}