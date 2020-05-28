package com.windaka.suizhi.manageport.elasticsearch.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CaptureCarES {
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

    private String carNumKeyword;

    private String carNumText;

    private Short carNumColor;

    private String carNumColorName;

    private Short carType;

    private String carTypeName;

    private Short carBrand;

    private String carBrandName;

    private Short carColor;

    private String carColorName;

    private String carImage;

    private Short carDirection;

    private String capImage;

    private String capVideo;

    private String type;

    private String fullImage;

    private String oriImage;

    private String carGroupDetailId;

    private String roomCode;

    private String roomName;

    private Short roomType;

    private String roomTypeName;

    private Short roomUseType;

    private String roomUseTypeName;

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
}
