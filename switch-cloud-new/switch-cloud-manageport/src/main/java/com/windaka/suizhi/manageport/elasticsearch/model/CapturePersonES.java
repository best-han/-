package com.windaka.suizhi.manageport.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class CapturePersonES {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String gbCode;

    private String gbCodeseq;

    private String dchnlRtsp;

    private String communityCode;

    private String communityName;

    private String personCode;

    private String personName;

    private Short personType;

    private String personTypeName;

    private String personPhone;

    private String personSex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date personBirthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkinTime;

    private Short personCountry;

    private String personCountryName;

    private Short personNationality;

    private String personNationalityName;

    private Short personPaperType;

    private String personPaperTypeName;

    private String personPaperNumber;

    private String personAddress;

    private String livePlace;

    private Short personMarriage;

    private String personMarriageName;

    private Short personPolitical;

    private String personPoliticalName;

    private Short personEducation;

    private String personEducationName;

    private String personOccupation;

    private String personImagePath;

    private Byte residencePermit;

    private Short relation;

    private String relationName;

    private String faceGroupDetailId;

    private Short groupType;

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

    private String roomCode;

    private String roomName;

}
