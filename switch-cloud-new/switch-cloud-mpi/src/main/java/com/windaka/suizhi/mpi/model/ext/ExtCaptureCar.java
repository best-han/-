package com.windaka.suizhi.mpi.model.ext;

import com.windaka.suizhi.mpi.model.CaptureCar;
import com.windaka.suizhi.mpi.model.CarGroupDetail;
import lombok.Data;

/**
 * @ClassName ExtCaptureCar
 * @Description 抓拍车
 * @Author lixianhua
 * @Date 2020/4/10 14:36
 * @Version 1.0
 */
@Data
public class ExtCaptureCar extends CaptureCar {

    // 布控库名称
    private String groupName;

    // 布控等级
    private String level;

    // 车布控明细ID
    private String detailId;

    // 报警等级
    private String detailLevel;

    // 报警等级名称
    private String detailLevelName;

    // 布控原因
    private String detailReason;

    // 布控库编码
    private String groupCode;

    // 布控人
    private String createUser;

    // 布控人ID
    private String createUserId;

}
