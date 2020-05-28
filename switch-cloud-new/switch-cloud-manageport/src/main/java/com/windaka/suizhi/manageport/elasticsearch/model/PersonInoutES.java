package com.windaka.suizhi.manageport.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PersonInoutES {

    private Integer id;

    private String communityCode;

    private String communityName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String personCode;

    private String personName;

    private Short personType;

    private String personTypeName;

    private String personPhone;

    private String personSex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    private String roomCode;

    private String roomName;

    private Short openResult;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkinTime;

    private Byte residencePermit;

    private Short relation;

    private String relationName;

    private String faceGroupDetailId;

    private Short groupType;

    private String livePlace;

    private String openResultName;

    private Short openType;

    private String openTypeName;

    private String channelName;

    private Short channelSeq;

    private String capImage;

    private String capVideo;

    //云端算法识别的 type
    private String type;

    //物业端上传的 type
    private String capType;
    
    private String capTypeName;
    
    private Short access;
}
