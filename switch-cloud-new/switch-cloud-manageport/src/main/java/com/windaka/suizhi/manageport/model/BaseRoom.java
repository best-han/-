package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BaseRoom {
    private Integer id;

    private String communityCode;

    private String communityName;

    private String code;

    private Short buildingNum;

    private String buildingName;

    private Short unitNum;

    private String unitName;

    private Short roomNum;

    private String roomName;

    private Short type;

    private String typeName;

    private BigDecimal buildingArea;

    private BigDecimal usableArea;

    private String ownership;

    private String buildingLocation;

    private Date opDate;

    private String buildingCode;

    private String unitCode;

    private Short useType;

    private String useTypeName;
}