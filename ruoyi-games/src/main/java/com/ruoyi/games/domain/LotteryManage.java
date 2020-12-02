package com.ruoyi.games.domain;

import java.util.Map;

public class LotteryManage implements java.io.Serializable {
    private String name;
    private String expect;
    private String openCode;
    private String openTime;
    private int groupId;
    private String id;
    private String code;
    private String roundCount;
    private int openStatus;
    private int kindId;
    private String status;
    private String startTime;
    private String endTime;
    private String fOpenTime;

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

    public String getfOpenTime() {
        return fOpenTime;
    }

    public void setfOpenTime(String fOpenTime) {
        this.fOpenTime = fOpenTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public int getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(String roundCount) {
        this.roundCount = roundCount;
    }
}
