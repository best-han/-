package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class CaptureDevice {
    private Integer id;

    private String code;

    private String name;

    private String type;

    private String typeName;

    private String location;

    private String communityCode;

    private String communityName;

    private Date opDate;

    private String gbCode;

    private String gbCodeSeq;

    private String dchnlRtsp;


}