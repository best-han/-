package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CarAccess {
    private Integer id;

    private String communityCode;

    private String communityName;

    private String carNum;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private Byte carDirect;

    private String devChnId;

    private String devChnName;

    private String devChnNum;

    private String parkingName;

    private String personCode;

    private String personName;

    private String capImage;

    private String capVideo;

    private String type;


}