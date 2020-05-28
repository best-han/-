package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CaptureDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureDeviceMapper {

    int insertDevice(CaptureDevice captureDevice);

    int deleteByCode(String code);

    int updateByCode(CaptureDevice captureDevice);

    List<CaptureDevice> selectDeviceList(Map<String, Object> params);

    /*
    zdq 级联修改设备 小区
     */
    int updateDeviceByCommunityCode(Map<String,Object> params);


}