package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class BasePet {
    private Integer id;

    private String code;

    private Short breed;

    private String breedName;

    private String image;

    private String communityCode;

    private String communityName;

    private String personCode;

    private Short mode;

    private String sex;

    private Short color;

    private String colorName;

    private Byte immune;

    private Integer count;

    private Date opDate;

    private Short adoption;

}