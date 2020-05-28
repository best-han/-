package com.windaka.suizhi.webapi.model;

import java.util.Date;

public class CaptureAbnormal {
    private Integer id;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String communityCode;

    private String communityName;

    private Short event;

    private String eventName;

    private Date beginTime;

    private Date endTime;

    private String capImage;

    private String capVideo;

    private Byte handelStatus;

    private Date handelTime;

    private String handelUser;

    private String handelUserId;

    private String handelConn;

    private String handelAdvise;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName == null ? null : deviceTypeName.trim();
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation == null ? null : deviceLocation.trim();
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode == null ? null : communityCode.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public Short getEvent() {
        return event;
    }

    public void setEvent(Short event) {
        this.event = event;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCapImage() {
        return capImage;
    }

    public void setCapImage(String capImage) {
        this.capImage = capImage == null ? null : capImage.trim();
    }

    public String getCapVideo() {
        return capVideo;
    }

    public void setCapVideo(String capVideo) {
        this.capVideo = capVideo == null ? null : capVideo.trim();
    }

    public Byte getHandelStatus() {
        return handelStatus;
    }

    public void setHandelStatus(Byte handelStatus) {
        this.handelStatus = handelStatus;
    }

    public Date getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(Date handelTime) {
        this.handelTime = handelTime;
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

    public String getHandelConn() {
        return handelConn;
    }

    public void setHandelConn(String handelConn) {
        this.handelConn = handelConn == null ? null : handelConn.trim();
    }

    public String getHandelAdvise() {
        return handelAdvise;
    }

    public void setHandelAdvise(String handelAdvise) {
        this.handelAdvise = handelAdvise == null ? null : handelAdvise.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}