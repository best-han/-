package com.windaka.suizhi.webapi.model.ext;

import com.windaka.suizhi.webapi.model.FaceGroup;
import com.windaka.suizhi.webapi.model.HtUser;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExtFaceGroup
 * @Description 人员布控库
 * @Author lixianhua
 * @Date 2020/4/8 10:10
 * @Version 1.0
 */
@Data
public class ExtFaceGroup extends FaceGroup {

    // 人员数量
    private Integer num;

    // 创建时间
    private String opTimeStr;

    // 人员集合
    private List<HtUser> userList;

}
