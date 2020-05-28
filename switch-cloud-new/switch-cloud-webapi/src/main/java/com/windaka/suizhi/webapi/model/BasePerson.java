package com.windaka.suizhi.webapi.model;

import java.util.Date;

public class BasePerson {
    private Integer id;

    private String code;

    private String communityCode;

    private String communityName;

    private String roomCode;

    private String name;

    private Short type;

    private String typeName;

    private String phone;

    private String sex;

    private String sexName;

    private Date birthday;

    private Short country;

    private String countryName;

    private Short nationality;

    private String nationalityName;

    private Short paperType;

    private String paperTypeName;

    private String paperNumber;

    private String address;

    private Short marriage;

    private String marriageName;

    private Short political;

    private String politicalName;

    private Short education;

    private String educationName;

    private String occupation;

    private String image;

    private Date checkinTime;

    private Byte residencePermit;

    private Short relation;

    private String relationName;

    private String faceGroupDetailId;

    private Date opDate;

    private String groupType;

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

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName == null ? null : sexName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Short getCountry() {
        return country;
    }

    public void setCountry(Short country) {
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public Short getNationality() {
        return nationality;
    }

    public void setNationality(Short nationality) {
        this.nationality = nationality;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName == null ? null : nationalityName.trim();
    }

    public Short getPaperType() {
        return paperType;
    }

    public void setPaperType(Short paperType) {
        this.paperType = paperType;
    }

    public String getPaperTypeName() {
        return paperTypeName;
    }

    public void setPaperTypeName(String paperTypeName) {
        this.paperTypeName = paperTypeName == null ? null : paperTypeName.trim();
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber == null ? null : paperNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Short getMarriage() {
        return marriage;
    }

    public void setMarriage(Short marriage) {
        this.marriage = marriage;
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName == null ? null : marriageName.trim();
    }

    public Short getPolitical() {
        return political;
    }

    public void setPolitical(Short political) {
        this.political = political;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName == null ? null : politicalName.trim();
    }

    public Short getEducation() {
        return education;
    }

    public void setEducation(Short education) {
        this.education = education;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName == null ? null : educationName.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Byte getResidencePermit() {
        return residencePermit;
    }

    public void setResidencePermit(Byte residencePermit) {
        this.residencePermit = residencePermit;
    }

    public Short getRelation() {
        return relation;
    }

    public void setRelation(Short relation) {
        this.relation = relation;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName == null ? null : relationName.trim();
    }

    public String getFaceGroupDetailId() {
        return faceGroupDetailId;
    }

    public void setFaceGroupDetailId(String faceGroupDetailId) {
        this.faceGroupDetailId = faceGroupDetailId == null ? null : faceGroupDetailId.trim();
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }
}