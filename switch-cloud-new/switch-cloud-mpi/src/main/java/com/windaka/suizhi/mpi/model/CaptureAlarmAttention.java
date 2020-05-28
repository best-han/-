package com.windaka.suizhi.mpi.model;

import java.util.Date;

public class CaptureAlarmAttention {
    private Integer id;

    private Integer attentionId;

    private String duration;

    private Integer level;

    private String levelName;

    private Date alarmTime;

    private Date lastCaptureTime;

    private String captureLocationCode;

    private String captureLocation;

    private String captureDevice;

    private String reason;

    private String personCode;

    private String name;

    private String phone;

    private String idNo;

    private String picUrl;

    private String attentionCode;

    private String attentionName;

    private Boolean handelStatus;

    private String handelUser;

    private String handelUserId;

    private Date handelTime;

    private String handelConn;

    private Date createTime;

    private String createUser;

    private String createUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Date getLastCaptureTime() {
        return lastCaptureTime;
    }

    public void setLastCaptureTime(Date lastCaptureTime) {
        this.lastCaptureTime = lastCaptureTime;
    }

    public String getCaptureLocationCode() {
        return captureLocationCode;
    }

    public void setCaptureLocationCode(String captureLocationCode) {
        this.captureLocationCode = captureLocationCode == null ? null : captureLocationCode.trim();
    }

    public String getCaptureLocation() {
        return captureLocation;
    }

    public void setCaptureLocation(String captureLocation) {
        this.captureLocation = captureLocation == null ? null : captureLocation.trim();
    }

    public String getCaptureDevice() {
        return captureDevice;
    }

    public void setCaptureDevice(String captureDevice) {
        this.captureDevice = captureDevice == null ? null : captureDevice.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getAttentionCode() {
        return attentionCode;
    }

    public void setAttentionCode(String attentionCode) {
        this.attentionCode = attentionCode == null ? null : attentionCode.trim();
    }

    public String getAttentionName() {
        return attentionName;
    }

    public void setAttentionName(String attentionName) {
        this.attentionName = attentionName == null ? null : attentionName.trim();
    }

    public Boolean getHandelStatus() {
        return handelStatus;
    }

    public void setHandelStatus(Boolean handelStatus) {
        this.handelStatus = handelStatus;
    }

    public String getHandelUser() {
        return handelUser;
    }

    public void setHandelUser(String handelUser) {
        this.handelUser = handelUser == null ? null : handelUser.trim();
    }

    public String getHandelUserId() {
        return handelUserId;
    }

    public void setHandelUserId(String handelUserId) {
        this.handelUserId = handelUserId == null ? null : handelUserId.trim();
    }

    public Date getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(Date handelTime) {
        this.handelTime = handelTime;
    }

    public String getHandelConn() {
        return handelConn;
    }

    public void setHandelConn(String handelConn) {
        this.handelConn = handelConn == null ? null : handelConn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }
}