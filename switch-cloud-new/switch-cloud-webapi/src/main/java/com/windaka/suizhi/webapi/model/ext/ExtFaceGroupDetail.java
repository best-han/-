package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;
import org.apache.poi.ss.usermodel.PictureData;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ExtFaceGroupDetail
 * @Description 人员布控实体类
 * @Author lixianhua
 * @Date 2020/4/17 9:11
 * @Version 1.0
 */
@Data
public class ExtFaceGroupDetail extends FaceGroupDetail {

    // 开始时间
    private String startTimeStr;

    // 结束时间
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

    // 模糊字段
    private String likeStr;

    // 人员ID
    private String  personCode;

    // 人员集合
    private List<HtUser> userList;

    // 是否分页
    private Boolean pageFlag;

    // 图片数据
    private PictureData picData;
}
