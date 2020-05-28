package com.windaka.suizhi.manageport.model;

import lombok.Data;

import java.util.Date;

@Data
public class JRoomRentRecord {
    private Long id;
    private String roomCode;
    private String communityCode;
    private String communityName;
    private Short useType;
    private String useTypeName;
    private String ownerName;
    private String phone;
    private String houseInfo;
    private Date createTime;
}
