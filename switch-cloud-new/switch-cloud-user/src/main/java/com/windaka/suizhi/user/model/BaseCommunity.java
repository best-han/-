package com.windaka.suizhi.user.model;

import java.math.BigDecimal;
import java.util.Date;

public class BaseCommunity {
    private Integer id;

    private String code;

    private String name;

    private String addressCode;

    private String address;

    private BigDecimal floorArea;

    private BigDecimal buildingArea;

    private String pmCompanyCode;

    private String pmCompanyName;

    private String pmCompanyPerson;

    private String pmCompanyPhone;

    private String estateDeveloperName;

    private String estateDeveloperPerson;

    private String estateDeveloperPhone;

    private Date opDate;

    private String area;

    private String areaName;

    private String location;

    private String locationArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getPmCompanyCode() {
        return pmCompanyCode;
    }

    public void setPmCompanyCode(String pmCompanyCode) {
        this.pmCompanyCode = pmCompanyCode == null ? null : pmCompanyCode.trim();
    }

    public String getPmCompanyName() {
        return pmCompanyName;
    }

    public void setPmCompanyName(String pmCompanyName) {
        this.pmCompanyName = pmCompanyName == null ? null : pmCompanyName.trim();
    }

    public String getPmCompanyPerson() {
        return pmCompanyPerson;
    }

    public void setPmCompanyPerson(String pmCompanyPerson) {
        this.pmCompanyPerson = pmCompanyPerson == null ? null : pmCompanyPerson.trim();
    }

    public String getPmCompanyPhone() {
        return pmCompanyPhone;
    }

    public void setPmCompanyPhone(String pmCompanyPhone) {
        this.pmCompanyPhone = pmCompanyPhone == null ? null : pmCompanyPhone.trim();
    }

    public String getEstateDeveloperName() {
        return estateDeveloperName;
    }

    public void setEstateDeveloperName(String estateDeveloperName) {
        this.estateDeveloperName = estateDeveloperName == null ? null : estateDeveloperName.trim();
    }

    public String getEstateDeveloperPerson() {
        return estateDeveloperPerson;
    }

    public void setEstateDeveloperPerson(String estateDeveloperPerson) {
        this.estateDeveloperPerson = estateDeveloperPerson == null ? null : estateDeveloperPerson.trim();
    }

    public String getEstateDeveloperPhone() {
        return estateDeveloperPhone;
    }

    public void setEstateDeveloperPhone(String estateDeveloperPhone) {
        this.estateDeveloperPhone = estateDeveloperPhone == null ? null : estateDeveloperPhone.trim();
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea == null ? null : locationArea.trim();
    }
}