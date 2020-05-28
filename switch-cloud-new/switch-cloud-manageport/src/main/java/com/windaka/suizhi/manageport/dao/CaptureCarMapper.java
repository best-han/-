package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CaptureCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureCarMapper {

    int insertCaptureCar(CaptureCar captureCar);

    int deleteById(String id);

    List<CaptureCar> selectCaptureCarList(Map<String, Object> params);
}