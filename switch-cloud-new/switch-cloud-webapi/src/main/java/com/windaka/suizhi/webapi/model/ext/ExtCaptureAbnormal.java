package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.CaptureAbnormal;
import com.windaka.suizhi.webapi.model.HtUserXq;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtCaptureAbnormal
 * @Description 异常信息实体类
 * @Author lixianhua
 * @Date 2020/4/1 10:13
 * @Version 1.0
 */
@Data
public class ExtCaptureAbnormal extends CaptureAbnormal {

    // 抓拍时间
    private String captureTimesStr;

    // 抓拍位置
    private String location;

    // 处理时间
    private String handelTimeStr;

    // 报警等级
    private String level;

    // 用户对应小区集合
    private List<HtUserXq> xqList;

    // 模糊查询字段
    private String likeStr;

    // 排序
    private String orderByClause;

}
