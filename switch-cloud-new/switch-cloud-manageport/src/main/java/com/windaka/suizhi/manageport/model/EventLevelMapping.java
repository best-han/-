package com.windaka.suizhi.manageport.model;

public class EventLevelMapping {
    private Integer id;

    private Short event;

    private Short level;

    private String levelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getEvent() {
        return event;
    }

    public void setEvent(Short event) {
        this.event = event;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }
}