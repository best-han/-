package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;


@Data
public class BasePerson {
    private Integer id;

    private String code;

    private String communityCode;

    private String communityName;

    private String roomCode;

    private String name;

    private Short type;

    private String typeName;

    private String phone;

    private String sex;

    private Date birthday;

    private Short country;

    private String countryName;

    private Short nationality;

    private String nationalityName;

    private Short paperType;

    private String paperTypeName;

    private String paperNumber;

    private String address;

    private Short marriage;

    private String marriageName;

    private Short political;

    private String politicalName;

    private Short education;

    private String educationName;

    private String occupation;

    private String image;

    private Date checkinTime;

    private Byte residencePermit;

    private Short relation;

    private String relationName;

    private String faceGroupDetailId;

    private Date opDate;

    private String sexName;

    private Short groupType;

    private String livePlace;

    private Short addressType;

    private Short liveType;
}