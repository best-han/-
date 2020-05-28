package com.windaka.suizhi.mpi.model;

import java.util.Date;

public class PersonInout {
    private Integer id;

    private String communityCode;

    private String communityName;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private String personCode;

    private String personName;

    private Short openResult;

    private String openResultName;

    private Short openType;

    private String openTypeName;

    private String channelName;

    private Short channelSeq;

    private String capImage;

    private String capVideo;

    private String capType;

    private String capTypeName;

    private Short access;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public Short getOpenResult() {
        return openResult;
    }

    public void setOpenResult(Short openResult) {
        this.openResult = openResult;
    }

    public String getOpenResultName() {
        return openResultName;
    }

    public void setOpenResultName(String openResultName) {
        this.openResultName = openResultName == null ? null : openResultName.trim();
    }

    public Short getOpenType() {
        return openType;
    }

    public void setOpenType(Short openType) {
        this.openType = openType;
    }

    public String getOpenTypeName() {
        return openTypeName;
    }

    public void setOpenTypeName(String openTypeName) {
        this.openTypeName = openTypeName == null ? null : openTypeName.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Short getChannelSeq() {
        return channelSeq;
    }

    public void setChannelSeq(Short channelSeq) {
        this.channelSeq = channelSeq;
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

    public String getCapType() {
        return capType;
    }

    public void setCapType(String capType) {
        this.capType = capType == null ? null : capType.trim();
    }

    public String getCapTypeName() {
        return capTypeName;
    }

    public void setCapTypeName(String capTypeName) {
        this.capTypeName = capTypeName == null ? null : capTypeName.trim();
    }

    public Short getAccess() {
        return access;
    }

    public void setAccess(Short access) {
        this.access = access;
    }
}