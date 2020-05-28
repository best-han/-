package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CaptureAlarmPerson {
    private Integer id;

    private String communityCode;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private Short deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String capImage;

    private String capVideo;

    private String detailId;

    private String detailImage;

    private Short detailLevel;

    private String detailLevelName;

    private String detailReason;

    private String detailPersonCode;

    private String detailPersonName;

    private String detailPersonPaperNum;

    private String detailPersonPhone;

    private String groupCode;

    private String groupName;

    private Byte handelStatus;

    private Date handelTime;

    private String handelUser;

    private String handelConn;

    private String contrastValue;


}