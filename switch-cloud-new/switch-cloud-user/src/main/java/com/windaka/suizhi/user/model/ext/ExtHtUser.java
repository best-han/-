package com.windaka.suizhi.user.model.ext;

import com.windaka.suizhi.user.model.HtUser;
import com.windaka.suizhi.user.model.HtUserXq;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ExtHtUser
 * @Description 用户实体类
 * @Author lixianhua
 * @Date 2020/4/22 9:30
 * @Version 1.0
 */
@Data
public class ExtHtUser extends HtUser {

    // 小区集合字符串
    private String xqCodes;

    // 小区集合
    private List<String> codeList;



    // 开始时间字符串
    private String startTime;

    // 开始时间
    private Date startDate;

    // 结束时间字符串
    private String endTime;

    // 结束时间
    private Date endDate;

    // 创建时间
    private String createTime;

    // 用户状态
    private String status;
}
