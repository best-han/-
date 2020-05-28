package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class PersonInout {
    private Integer id;

    private String communityCode;

    private String communityName;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String personCode;

    private String personName;

    private Short openResult;

    private String openResultName;

    private Short openType;

    private String openTypeName;

    private String channelName;

    private Short channelSeq;

    private String capImage;

    private String capVideo;

    private String capType;

    private String capTypeName;

}