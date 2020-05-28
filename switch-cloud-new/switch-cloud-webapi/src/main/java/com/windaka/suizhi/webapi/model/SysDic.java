package com.windaka.suizhi.webapi.model;


import lombok.Data;

import java.util.Date;

@Data
public class SysDic {
    private Integer id;

    private String dicCode;

    private String dicName;

    private Date opDate;


}