package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.CaptureAlarmAttention;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtCaptureAlarmAttention
 * @Description 重点关注预警实体类
 * @Author lixianhua
 * @Date 2020/4/28 10:09
 * @Version 1.0
 */
@Data
public class ExtCaptureAlarmAttention extends CaptureAlarmAttention {

    // 模糊字段
    private String likeStr;

    // 人员集合
    private List<HtUser> userList;

    // 报警时间
    private String alarmTimeStr;

    // 末次感知时间
    private String lastCaptureTimeStr;

    // 处理时间
    private String handelTimeStr;

    // 小区名称
    private String xqName;

    // 排序
    private String orderByClause;
}
