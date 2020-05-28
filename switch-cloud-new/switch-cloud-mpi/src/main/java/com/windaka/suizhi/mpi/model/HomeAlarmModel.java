package com.windaka.suizhi.mpi.model;

import lombok.Data;

/**
 * @ClassName HomeAlarmModel
 * @Description 首页弹窗实体类
 * @Author lixianhua
 * @Date 2020/4/10 8:25
 * @Version 1.0
 */
@Data
public class HomeAlarmModel {

    // 主键
    private String id;

    // 抓拍地点
    private String location;

    // 小区名称
    private String xqName;

    // 抓拍时间
    private String captureTimeStr;

    // 时间戳（前端识别）
    private String timeStamp;

    // 抓拍人姓名
    private String personName;

    // 抓拍图片
    private String img;

    // 人员布控图片
    private String groupPic;

    // 库名称
    private String groupName;

    // 事件类型
    private String event;

    // 相似度
    private String similar;

    // 车牌号
    private String carNum;

    // 报警等级
    private String level;

    // 设备名称（地址）
    private String deviceName;

    // 未感知时长
    private String duration;
}
