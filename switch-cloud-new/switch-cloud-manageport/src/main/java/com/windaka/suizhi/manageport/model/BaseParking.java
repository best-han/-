package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseParking {
    private Integer id;

    private String code;

    private String num;

    private String personCode;

    private String communityCode;

    private String communityName;

    private Date opTime;

}
