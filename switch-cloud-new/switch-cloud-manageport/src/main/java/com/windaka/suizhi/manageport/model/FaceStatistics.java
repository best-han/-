package com.windaka.suizhi.manageport.model;

public class FaceStatistics {
    private Integer id;

    private String faceId;

    private Byte type;

    private String records;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId == null ? null : faceId.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records == null ? null : records.trim();
    }
}