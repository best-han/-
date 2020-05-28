package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class OperateRecord {
    private Integer id;

    private String opTable;

    private String opTableCode;

    private String opTableId;

    private String opName;

    private String opUserId;

    private Date opTime;

    public OperateRecord(String opTable, String opTableCode, String opTableId, String opName, String opUserId) {
        this.opTable = opTable;
        this.opTableCode = opTableCode;
        this.opTableId = opTableId;
        this.opName = opName;
        this.opUserId = opUserId;
    }
}