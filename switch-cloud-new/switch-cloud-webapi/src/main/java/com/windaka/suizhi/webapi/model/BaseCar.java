package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseCar {
    private Integer id;

    private String num;

    private Short numColor;

    private String numColorName;

    private Short color;

    private String colorName;

    private Short type;

    private String typeName;

    private Short brand;

    private String brandName;

    private String image;

    private String personCode;

    private String communityCode;

    private String communityName;

    private String carGroupDetailId;

    private Date opTime;


}