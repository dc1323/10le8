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

    private int batchID;
    private int kindID;
    private int serverID;
    private String kindName;
    private int cellScore;
    private int androidCount;
    private BigDecimal score;
    private int enterMinInterval;
    private int enterMaxInterval;
    private int leaveMinInterval;
    private int leaveMaxInterval;
    private int nullity;
    private long takeMinScore;
    private int showCellScore;
    private int checkEnter;
    private int checkLeave;

    public long takeMaxScore;
    public int switchMinInnings;
    public int switchMaxInnings;

    public int enterTime;
    public int leaveTime;
    public int serviceMode;

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getServiceMode() {
        return serviceMode;
    }

    public void setServiceMode(int serviceMode) {
        this.serviceMode = serviceMode;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }

    public void setEnterMinInterval(int enterMinInterval) {
        this.enterMinInterval = enterMinInterval;
    }

    public void setEnterMaxInterval(int enterMaxInterval) {
        this.enterMaxInterval = enterMaxInterval;
    }

    public void setLeaveMinInterval(int leaveMinInterval) {
        this.leaveMinInterval = leaveMinInterval;
    }

    public void setLeaveMaxInterval(int leaveMaxInterval) {
        this.leaveMaxInterval = leaveMaxInterval;
    }

    public void setNullity(int nullity) {
        this.nullity = nullity;
    }

    public long getTakeMaxScore() {
        return takeMaxScore;
    }

    public void setTakeMaxScore(long takeMaxScore) {
        this.takeMaxScore = takeMaxScore;
    }

    public int getSwitchMinInnings() {
        return switchMinInnings;
    }

    public void setSwitchMinInnings(int switchMinInnings) {
        this.switchMinInnings = switchMinInnings;
    }

    public int getSwitchMaxInnings() {
        return switchMaxInnings;
    }

    public void setSwitchMaxInnings(int switchMaxInnings) {
        this.switchMaxInnings = switchMaxInnings;
    }

    public int getCheckEnter() {
        return checkEnter;
    }

    public void setCheckEnter(int checkEnter) {
        this.checkEnter = checkEnter;
    }

    public int getCheckLeave() {
        return checkLeave;
    }

    public void setCheckLeave(int checkLeave) {
        this.checkLeave = checkLeave;
    }

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

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

    public int getCellScore() {
        return cellScore;
    }

    public void setCellScore(int cellScore) {
        this.cellScore = cellScore;
    }

    public int getAndroidCount() {
        return androidCount;
    }

    public void setAndroidCount(int androidCount) {
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

    public int getNullity() {
        return nullity;
    }

    public long getTakeMinScore() {
        return takeMinScore;
    }

    public void setTakeMinScore(long takeMinScore) {
        this.takeMinScore = takeMinScore;
    }

    public int getShowCellScore() {
        return showCellScore;
    }

    public void setShowCellScore(int showCellScore) {
        this.showCellScore = showCellScore;
    }

    public String getName() {
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
