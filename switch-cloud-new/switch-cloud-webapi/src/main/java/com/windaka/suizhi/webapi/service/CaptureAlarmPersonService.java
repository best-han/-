package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface CaptureAlarmPersonService {

    //    告警记录列表查询
    List<Map<String,Object>> queryAlarmPersonList(Map<String,Object> params) throws OssRenderException;
}
