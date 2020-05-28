package com.windaka.suizhi.mpi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CaptureCarService {

    //   socket 告警记录列表查询 推送(车辆)
    List<Map<String,Object>> queryCapCarList(Map<String, Object> params) throws OssRenderException;

}
