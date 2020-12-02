package com.ruoyi.games.domain;

public class CaiPiaoDiZhi implements java.io.Serializable {
    private int id;
    private String code;
    private String name;
    private String url;
    private String startTime;
    private String endTime;
    private int groupId;
    private int interval;
    private int httpInterval;
    private int nullity;
    private int sortId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getHttpInterval() {
        return httpInterval;
    }

    public void setHttpInterval(int httpInterval) {
        this.httpInterval = httpInterval;
    }

    public int getNullity() {
        return nullity;
    }

    public void setNullity(int nullity) {
        this.nullity = nullity;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }
}
