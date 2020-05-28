package com.windaka.suizhi.manageport.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CarAccessES {
    
    private Integer id;

    /*
    小区相关
     */
    private String communityCode;//

    private String communityName;//

    /**
     * 车辆相关
     */
    /*
    carNumKeyword 用于车牌号聚合
     */
    private String carNumKeyword;//

    /*
    carNumText 用于车牌号模糊查询 而carNumText=carNumKeyword
     */
    private String carNumText;//
    
    private Short carNumColor;

    private String carNumColorName;

    private Short carColor;

    private String carColorName;

    private Short carType;

    private String carTypeName;

    private Short carBrand;

    private String carBrandName;

    private String carImage;

    private String carGroupDetailId;

    /*
    设备相关
     */
    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    /*
    人员相关
     */
    private String personCode;//

    private String personName;//

    private Short personType;

    private String personTypeName;//

    private String personPhone;//

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkinTime;

    private Byte residencePermit;

    private Short relation;

    private String relationName;

    private String faceGroupDetailId;

    private Short groupType;

    private String livePlace;

    /*
    房间相关
     */
    private String roomCode;//

    private String roomName;

    private Short roomType;

    private String roomTypeName;

    private Short roomUseType;

    private String roomUseTypeName;

    /*
    抓拍相关
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTime;//

    private String devChnId;//

    private String devChnName;//

    private String devChnNum;//

    private String parkingName;

    private Byte carDirect;//

    private String capImage;//

    private String capVideo;

    private String type;//云端分析 正常/陌生 主要看实有人口中有无其信息

}
