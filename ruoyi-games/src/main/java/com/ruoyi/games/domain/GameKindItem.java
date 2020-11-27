package com.ruoyi.games.domain;

public class GameKindItem {

    private Integer kindID;

    private Integer gameID;

    private int typeID;

    private int joinID;

    private int sortID;

    private String kindName;

    private String processName;

    private String gameRuleUrl;

    private String downLoadUrl;

    private int recommend;

    private int gameFlag;

    private String nullity;

    public Integer getKindID() {
        return kindID;
    }

    public void setKindID(Integer kindID) {
        this.kindID = kindID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getJoinID() {
        return joinID;
    }

    public void setJoinID(int joinID) {
        this.joinID = joinID;
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getGameRuleUrl() {
        return gameRuleUrl;
    }

    public void setGameRuleUrl(String gameRuleUrl) {
        this.gameRuleUrl = gameRuleUrl;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getGameFlag() {
        return gameFlag;
    }

    public void setGameFlag(int gameFlag) {
        this.gameFlag = gameFlag;
    }

    public String getNullity() {
        return nullity;
    }

    public void setNullity(String nullity) {
        this.nullity = nullity;
    }
}
