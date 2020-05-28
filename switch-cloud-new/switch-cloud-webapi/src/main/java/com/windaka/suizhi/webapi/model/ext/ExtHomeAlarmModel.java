package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.HtUserXq;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtHomeAlarmModel
 * @Description 首页警告实体
 * @Author lixianhua
 * @Date 2020/4/10 18:04
 * @Version 1.0
 */
@Data
public class ExtHomeAlarmModel {

    // 主键
    private Integer id;

    private String type;

    private String libraryImg;

    private String img;

    private String event;

    private String personName;

    private String groupName;

    private String captureTime;

    private String captureTimeStr;

    private String xqName;

    private String carNum;

    private String alarmType;

    private List<HtUser> userList;

    private List<HtUserXq> xqList;

}
