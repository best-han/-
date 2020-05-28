package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface CaptureAlarmCarService {

    //   socket 告警记录列表查询（车辆） 推送
    List<Map<String,Object>> queryCapCarList(Map<String,Object> params) throws OssRenderException;
}
