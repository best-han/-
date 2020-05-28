package com.windaka.suizhi.mpi.model;

import java.util.Date;

public class CarAccess {
    private Integer id;

    private String communityCode;

    private String communityName;

    private String carNum;

    private Date captureTime;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String deviceTypeName;

    private String deviceLocation;

    private Byte carDirect;

    private String devChnId;

    private String devChnName;

    private String devChnNum;

    private String parkingName;

    private String personCode;

    private String personName;

    private String capImage;

    private String capVideo;

    private String oriImage;

    private String type;

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

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum == null ? null : carNum.trim();
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

    public Byte getCarDirect() {
        return carDirect;
    }

    public void setCarDirect(Byte carDirect) {
        this.carDirect = carDirect;
    }

    public String getDevChnId() {
        return devChnId;
    }

    public void setDevChnId(String devChnId) {
        this.devChnId = devChnId == null ? null : devChnId.trim();
    }

    public String getDevChnName() {
        return devChnName;
    }

    public void setDevChnName(String devChnName) {
        this.devChnName = devChnName == null ? null : devChnName.trim();
    }

    public String getDevChnNum() {
        return devChnNum;
    }

    public void setDevChnNum(String devChnNum) {
        this.devChnNum = devChnNum == null ? null : devChnNum.trim();
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName == null ? null : parkingName.trim();
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

    public String getOriImage() {
        return oriImage;
    }

    public void setOriImage(String oriImage) {
        this.oriImage = oriImage == null ? null : oriImage.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}