package com.windaka.suizhi.mpi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface CapturePersonService {

    //   socket 告警记录列表查询 推送
    List<Map<String,Object>> queryCapPersonList(Map<String, Object> params) throws OssRenderException;
}
