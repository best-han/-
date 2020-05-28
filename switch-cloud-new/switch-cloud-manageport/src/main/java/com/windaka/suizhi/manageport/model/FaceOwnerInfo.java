package com.windaka.suizhi.manageport.model;

public class FaceOwnerInfo {
    private Integer id;

    private String communityCode;

    private String personCode;

    private String personFace;

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

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getPersonFace() {
        return personFace;
    }

    public void setPersonFace(String personFace) {
        this.personFace = personFace == null ? null : personFace.trim();
    }
}