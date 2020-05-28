package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CapturePerson {
    private Integer id;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String communityCode;

    private String communityName;

    private String personCode;

    private String personName;

    private Short personType;

    private String personTypeName;

    private String personPhone;

    private String personSex;

    private Date personBirthday;

    private Short personCountry;

    private String personCountryName;

    private Short personNationality;

    private String personNationalityName;

    private Short personPaperType;

    private String personPaperTypeName;

    private String personPaperNumber;

    private String personAddress;

    private Short personMarriage;

    private String personMarriageName;

    private Short personPolitical;

    private String personPoliticalName;

    private Short personEducation;

    private String personEducationName;

    private String personOccupation;

    private String personImagePath;

    private Byte type;

    private String capSex;

    private String capAge;

    private String capCountry;

    private Byte capBmask;

    private Byte capEyeglass;

    private Byte capKnapsack;

    private Short capCoatType;

    private String capCoatTypeName;

    private Short capCoatColor;

    private String capCoatColorName;

    private Short capTrousersType;

    private String capTrousersTypeName;

    private Short capTrousersColor;

    private String capTrousersColorName;

    private Byte capHat;

    private Byte capHandbag;

    private Short capType;

    private String capPersonImage;

    private String capImage;

    private String capVideo;
}