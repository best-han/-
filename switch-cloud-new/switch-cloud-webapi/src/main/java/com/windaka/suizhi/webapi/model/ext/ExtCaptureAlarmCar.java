package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.CaptureAlarmCar;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtCaptureAlarmCar
 * @Description 车辆预警类
 * @Author lixianhua
 * @Date 2020/4/1 9:36
 * @Version 1.0
 */
@Data
public class ExtCaptureAlarmCar extends CaptureAlarmCar {

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

    // 排序
    private String orderByClause;
}
