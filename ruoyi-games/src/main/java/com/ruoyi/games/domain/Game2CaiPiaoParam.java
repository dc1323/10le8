package com.ruoyi.games.domain;

public class Game2CaiPiaoParam implements java.io.Serializable {
    public int id;
    public int kindID;
    public String kindName;
    public int cbFreeTime;
    public int cbBetTime;
    public int cbEndTime;
    public int totalEndTime;
    public int totalTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKindID() {
        return kindID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public int getCbFreeTime() {
        return cbFreeTime;
    }

    public void setCbFreeTime(int cbFreeTime) {
        this.cbFreeTime = cbFreeTime;
    }

    public int getCbBetTime() {
        return cbBetTime;
    }

    public void setCbBetTime(int cbBetTime) {
        this.cbBetTime = cbBetTime;
    }

    public int getCbEndTime() {
        return cbEndTime;
    }

    public void setCbEndTime(int cbEndTime) {
        this.cbEndTime = cbEndTime;
    }

    public int getTotalEndTime() {
        return totalEndTime;
    }

    public void setTotalEndTime(int totalEndTime) {
        this.totalEndTime = totalEndTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
