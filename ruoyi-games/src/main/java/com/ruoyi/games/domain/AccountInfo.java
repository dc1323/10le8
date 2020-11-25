package com.ruoyi.games.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccountInfo {

    private Integer userID;
    private Integer gameID;
    private String accounts;
    private int isAgent;
    private String registerMobile;
    private String compellation;
    private int userType;
    private String playTimeCount;
    private String playingGame;
    private Date registerDate;
    private String lastLogonIp;
    private Date lastLogonDate;
    private int nullity;

    /**
     * 账户余额
     */
    private String score;
    private String insureScore;
    private String orderAmount;
    private String money;
    private String totalCommission;

    private String keyWord;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

    private int total;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public int getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(int isAgent) {
        this.isAgent = isAgent;
    }

    public String getRegisterMobile() {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile) {
        this.registerMobile = registerMobile;
    }

    public String getCompellation() {
        return compellation;
    }

    public void setCompellation(String compellation) {
        this.compellation = compellation;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPlayTimeCount() {
        return playTimeCount;
    }

    public void setPlayTimeCount(String playTimeCount) {
        this.playTimeCount = playTimeCount;
    }

    public String getPlayingGame() {
        return playingGame;
    }

    public void setPlayingGame(String playingGame) {
        this.playingGame = playingGame;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLastLogonIp() {
        return lastLogonIp;
    }

    public void setLastLogonIp(String lastLogonIp) {
        this.lastLogonIp = lastLogonIp;
    }

    public Date getLastLogonDate() {
        return lastLogonDate;
    }

    public void setLastLogonDate(Date lastLogonDate) {
        this.lastLogonDate = lastLogonDate;
    }

    public int getNullity() {
        return nullity;
    }

    public void setNullity(int nullity) {
        this.nullity = nullity;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getInsureScore() {
        return insureScore;
    }

    public void setInsureScore(String insureScore) {
        this.insureScore = insureScore;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
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

    public String getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(String totalCommission) {
        this.totalCommission = totalCommission;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
