package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BaseCommunity {
    private Integer id;

    private String code;

    private String name;

    private String addressCode;

    private String address;

    private BigDecimal floorArea;

    private BigDecimal buildingArea;

    private String pmCompanyCode;

    private String pmCompanyName;

    private String pmCompanyPerson;

    private String pmCompanyPhone;

    private String estateDeveloperName;

    private String estateDeveloperPerson;

    private String estateDeveloperPhone;

    private Date opDate;

    private String area;

    private String areaName;

    private String location;

   private String locationArea;

    private String subdistrictCode;

    private String subdistrictName;
}