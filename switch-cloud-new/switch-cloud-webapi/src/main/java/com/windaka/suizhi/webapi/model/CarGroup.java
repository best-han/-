package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class CarGroup {
    private Integer id;

    private String code;

    private Short type;

    private String typeName;

    private String name;

    private String remarks;

    private Short level;

    private String levelName;

    private Date opTime;

    private String opUser;

    private String opUserId;


}