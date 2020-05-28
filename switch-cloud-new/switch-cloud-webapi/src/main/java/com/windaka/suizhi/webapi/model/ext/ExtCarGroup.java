package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.CarGroup;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtCarGroup
 * @Description 车辆库实体类
 * @Author lixianhua
 * @Date 2020/4/14 16:01
 * @Version 1.0
 */
@Data
public class ExtCarGroup extends CarGroup {

    // 人员数量
    private Integer num;

    // 创建时间
    private String opTimeStr;

    // 人员集合
    private List<HtUser> userList;

}
