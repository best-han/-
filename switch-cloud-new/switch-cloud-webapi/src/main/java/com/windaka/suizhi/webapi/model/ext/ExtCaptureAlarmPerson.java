package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.CaptureAlarmPerson;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtCaptureAlarmPerson
 * @Description 人员预警类
 * @Author lixianhua
 * @Date 2020/4/1 8:13
 * @Version 1.0
 */
@Data
public class ExtCaptureAlarmPerson extends CaptureAlarmPerson {

    // 抓拍时间
    private String captureTimesStr;

    // 抓拍位置
    private String location;

    // 用户集合
    private List<HtUser> userList;

    // 模糊字段
    private String likeStr;

    // 处理时间
    private String handelTimeStr;

    // 事件类型名称
    private String eventName;

    // 排序
    private String orderByClause;

    // 抓拍小图
    private String capSmallImage;


}
