package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CaptureAnimal {
    private Integer id;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String communityCode;

    private String communityName;

    private Short capBreed;

    private String capBreedName;

    private Short capColor;

    private String capColorName;

    private Byte capRope;

    private String capImage;

    private String capVideo;


}