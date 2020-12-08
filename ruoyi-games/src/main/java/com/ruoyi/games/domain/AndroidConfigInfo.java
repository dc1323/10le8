package com.ruoyi.games.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/7 23:01
 */
public class AndroidConfigInfo {

    private Integer batchID;
    private Integer kindID;
    private String kindName;
    private String name;
    private BigDecimal cellScore;
    private Integer androidCount;
    private BigDecimal score;
    private Integer enterMinInterval;
    private Integer enterMaxInterval;
    private Integer leaveMinInterval;
    private Integer leaveMaxInterval;
    private Integer nullity;
    private Integer serverID;
    private BigDecimal takeMinScore;
    private BigDecimal showCellScore;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public Integer getKindID() {
        return kindID;
    }

    public void setKindID(Integer kindID) {
        this.kindID = kindID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public BigDecimal getCellScore() {
        return cellScore;
    }

    public void setCellScore(BigDecimal cellScore) {
        this.cellScore = cellScore;
    }

    public Integer getAndroidCount() {
        return androidCount;
    }

    public void setAndroidCount(Integer androidCount) {
        this.androidCount = androidCount;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getEnterMinInterval() {
        return enterMinInterval;
    }

    public void setEnterMinInterval(Integer enterMinInterval) {
        this.enterMinInterval = enterMinInterval;
    }

    public Integer getEnterMaxInterval() {
        return enterMaxInterval;
    }

    public void setEnterMaxInterval(Integer enterMaxInterval) {
        this.enterMaxInterval = enterMaxInterval;
    }

    public Integer getLeaveMinInterval() {
        return leaveMinInterval;
    }

    public void setLeaveMinInterval(Integer leaveMinInterval) {
        this.leaveMinInterval = leaveMinInterval;
    }

    public Integer getLeaveMaxInterval() {
        return leaveMaxInterval;
    }

    public void setLeaveMaxInterval(Integer leaveMaxInterval) {
        this.leaveMaxInterval = leaveMaxInterval;
    }

    public Integer getNullity() {
        return nullity;
    }

    public void setNullity(Integer nullity) {
        this.nullity = nullity;
    }

    public Integer getServerID() {
        return serverID;
    }

    public void setServerID(Integer serverID) {
        this.serverID = serverID;
    }

    public BigDecimal getTakeMinScore() {
        return takeMinScore;
    }

    public void setTakeMinScore(BigDecimal takeMinScore) {
        this.takeMinScore = takeMinScore;
    }

    public BigDecimal getShowCellScore() {
        return showCellScore;
    }

    public void setShowCellScore(BigDecimal showCellScore) {
        this.showCellScore = showCellScore;
    }

    public String getName() {
        if (null == cellScore) {
            return kindName;
        }
        return kindName + "_" + cellScore;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
