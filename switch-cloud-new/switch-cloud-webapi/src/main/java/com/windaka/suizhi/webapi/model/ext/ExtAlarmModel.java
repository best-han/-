package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.HtUserXq;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtAlarmModel
 * @Description 预警实体类
 * @Author lixianhua
 * @Date 2020/4/28 11:43
 * @Version 1.0
 */
@Data
public class ExtAlarmModel {

    // 用户集合
    private List<HtUser> userList;

    // 用户关联小区集合
    private List<HtUserXq> xqList;

    // 表名
    private String tableName;
}
