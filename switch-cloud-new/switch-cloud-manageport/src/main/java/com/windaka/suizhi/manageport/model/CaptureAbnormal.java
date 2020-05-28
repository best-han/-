package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CaptureAbnormal {
    private Integer id;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String communityCode;

    private String communityName;

    private Short event;

    private String eventName;

    private Date beginTime;

    private Date endTime;

    private String capImage;

    private String capVideo;

    private Byte handelStatus;

    private Date handelTime;

    private String handelUser;

    private String handelConn;

    private String handelAdvise;

    private String code;

}