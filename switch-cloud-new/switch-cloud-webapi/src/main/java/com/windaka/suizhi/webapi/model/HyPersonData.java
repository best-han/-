package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.Date;


@Data
public class HyPersonData {
    private Integer id;

    private String name;

    private String paperType;

    private String paperNum;

    private String sex;

    private String birthday;

    private String nation;

    private String address;

    private String phone;

    private String typename;

    private String addresscode;

    private String marriage;

    private String education;

    private String political;

    private String fulladdress;

    private Date creTime;

    private Date updTime;


}