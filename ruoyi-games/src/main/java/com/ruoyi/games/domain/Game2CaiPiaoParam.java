package com.ruoyi.games.domain;

import java.util.Map;

public class Game2CaiPiaoParam implements java.io.Serializable {
    public int id;
    public int kindID;
    public String kindName;
    public int cbCloseAccountTime;
    public int cbEnterTime;
    public int cbStartTime;
    public int maxRoundCount;
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
        return cbCloseAccountTime;
    }

    public void setCbFreeTime(int cbCloseAccountTime) {
        this.cbCloseAccountTime = cbCloseAccountTime;
    }

    public int getCbBetTime() {
        return cbEnterTime;
    }

    public void setCbBetTime(int cbEnterTime) {
        this.cbEnterTime = cbEnterTime;
    }

    public int getCbEndTime() {
        return cbStartTime;
    }

    public void setCbEndTime(int cbStartTime) {
        this.cbStartTime = cbStartTime;
    }

    public int getmaxRoundCount() {
        return maxRoundCount;
    }

    public void setmaxRoundCount(int maxRoundCount) {
        this.maxRoundCount = maxRoundCount;
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
