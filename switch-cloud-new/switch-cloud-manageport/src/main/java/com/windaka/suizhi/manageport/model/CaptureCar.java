package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CaptureCar {
    private Integer id;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String communityCode;

    private String communityName;

    private String carNum;

    private Short carNumColor;

    private String carNumColorName;

    private Short carType;

    private String carTypeName;

    private Short carBrand;

    private String carBrandName;

    private Short carColor;

    private String carColorName;

    private String carImage;

    private Byte carDirection;

    private String capImage;

    private String capVideo;

    private String personCode;

    private String personName;

    private String type;

    private String fullImage;

}