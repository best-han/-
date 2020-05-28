package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.FaceAttentionDetail;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ExtFaceAttentionDetail
 * @Description 重点关注人员实体类
 * @Author lixianhua
 * @Date 2020/4/18 11:24
 * @Version 1.0
 */
@Data
public class ExtFaceAttentionDetail extends FaceAttentionDetail {

    // 人员ID
    private String personCode;

    // 布控开始时间
    private String startTimeStr;

    // 布控结束时间
    private String endTimeStr;

    // 撤控时间
    private String withdrawTimeStr;

    // 撤控开始时间
    private Date withdrawStart;

    // 撤控开始时间字符串
    private String withdrawStartStr;

    // 撤控结束时间字符串
    private String withdrawEndStr;

    // 撤控结束时间
    private Date withdrawEnd;

    // 布控时间段
    private String controlPeriods;

    // 加入时间
    private String comeInTimeStr;

    // 加入开始时间
    private Date comeInStart;

    // 加入开始时间字符串
    private String comeInStartStr;

    // 加入结束时间
    private Date comeInEnd;

    // 加入结束时间字符串
    private String comeInEndStr;

    // 模糊字段
    private String likeStr;

    // 人员集合
    private List<HtUser> userList;

    // 人员code集合
    private List<String> codeList;

    // 是否分页
    private Boolean pageFlag;

}
