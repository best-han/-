package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CaptureCarService {

    //   socket 告警记录列表查询 推送(车辆)
    List<Map<String,Object>> queryCapCarList(Map<String,Object> params) throws OssRenderException;

    /**
     * @description: 车辆实时抓拍查询
     * @author wcl
     * @date 2020/4/10
     */

    Map<String,Object> queryCarCaptureList(@Param("params") Map<String,Object> params);

    //车辆抓拍列表 通过ES 查询
    Map<String,Object> queryCarCaptureListByES(Map<String,Object> params);

}
