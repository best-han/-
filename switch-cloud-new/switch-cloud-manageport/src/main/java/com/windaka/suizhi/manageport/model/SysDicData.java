package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysDicData {
    private Integer id;

    private String dicCode;

    private Short dicKey;

    private String dicValue;

    private Date opDate;

}