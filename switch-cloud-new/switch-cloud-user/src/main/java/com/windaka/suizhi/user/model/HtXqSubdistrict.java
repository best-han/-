package com.windaka.suizhi.user.model;

public class HtXqSubdistrict {
    private Long id;

    private String subdistrictId;

    private String xqCode;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubdistrictId() {
        return subdistrictId;
    }

    public void setSubdistrictId(String subdistrictId) {
        this.subdistrictId = subdistrictId == null ? null : subdistrictId.trim();
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