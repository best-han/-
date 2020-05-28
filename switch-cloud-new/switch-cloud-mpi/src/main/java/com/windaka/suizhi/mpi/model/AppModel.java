package com.windaka.suizhi.mpi.model;

import lombok.Data;

/**
 * @ClassName AppModel
 * @Description 警务通弹窗实体
 * @Author lixianhua
 * @Date 2020/5/15 17:00
 * @Version 1.0
 */
@Data
public class AppModel {

    // 主类型
    private String type;

    // 副类型
    private String subType;

    // 数量
    private Integer num;
}
