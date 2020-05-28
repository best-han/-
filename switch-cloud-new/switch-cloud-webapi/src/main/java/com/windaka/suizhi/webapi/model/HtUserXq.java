package com.windaka.suizhi.webapi.model;

public class HtUserXq {
    private Long id;

    private String userId;

    private String xqCode;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getXqCode() {
        return xqCode;
    }

    public void setXqCode(String xqCode) {
        this.xqCode = xqCode == null ? null : xqCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}