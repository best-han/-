package com.windaka.suizhi.manageport.model;

import java.util.Date;

public class BaseCar {
    private Integer id;

    private String num;

    private Short numColor;

    private String numColorName;

    private Short color;

    private String colorName;

    private Short type;

    private String typeName;

    private Short brand;

    private String brandName;

    private String image;

    private String personCode;

    private String communityCode;

    private String communityName;

    private String carGroupDetailId;

    private Date opTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public Short getNumColor() {
        return numColor;
    }

    public void setNumColor(Short numColor) {
        this.numColor = numColor;
    }

    public String getNumColorName() {
        return numColorName;
    }

    public void setNumColorName(String numColorName) {
        this.numColorName = numColorName == null ? null : numColorName.trim();
    }

    public Short getColor() {
        return color;
    }

    public void setColor(Short color) {
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Short getBrand() {
        return brand;
    }

    public void setBrand(Short brand) {
        this.brand = brand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
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

    public String getCarGroupDetailId() {
        return carGroupDetailId;
    }

    public void setCarGroupDetailId(String carGroupDetailId) {
        this.carGroupDetailId = carGroupDetailId == null ? null : carGroupDetailId.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}