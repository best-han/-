package com.windaka.suizhi.manageport.model;

public class FaceGroupInfo {
    private Integer id;

    private String communityCode;

    private String faceDetailId;

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

    public String getFaceDetailId() {
        return faceDetailId;
    }

    public void setFaceDetailId(String faceDetailId) {
        this.faceDetailId = faceDetailId == null ? null : faceDetailId.trim();
    }

    public String getPersonFace() {
        return personFace;
    }

    public void setPersonFace(String personFace) {
        this.personFace = personFace == null ? null : personFace.trim();
    }
}