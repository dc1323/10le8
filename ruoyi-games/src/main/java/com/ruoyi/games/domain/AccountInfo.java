package com.ruoyi.games.domain;

import com.ruoyi.common.utils.AddressUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class AccountInfo {

    private Integer userID;
    private Integer gameID;
    private String accounts;
    private int isAgent;
    private String nickName;
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
    private String commission;
    /**
     * 佣金总额
     */
    private String totalMoney;

    private Integer kindID;
    private String kindName;
    private Integer serverID;
    private Date collectDate;
    private String totalScore;
    private int isAndroid;
    private String agentName;
    private String advertiser;
    private String advertPlat;
    private String lastLogonIPAddress;

    private String keyWord;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        return AddressUtils.getRealAddressByIP(lastLogonIp);
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
        if (null == score || "".equals(score)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(score);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getInsureScore() {
        if (null == insureScore || "".equals(insureScore)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(insureScore);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setInsureScore(String insureScore) {
        this.insureScore = insureScore;
    }

    public String getOrderAmount() {
        if (null == orderAmount || "".equals(orderAmount)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(orderAmount);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getMoney() {
        if (null == money || "".equals(money)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(money);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
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
        if (null == totalCommission || "".equals(totalCommission)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(totalCommission);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setTotalCommission(String totalCommission) {
        this.totalCommission = totalCommission;
    }

    public String getCommission() {
        if (null == commission || "".equals(commission)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(commission);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTotalMoney() {
        if (null == totalCommission || "".equals(totalCommission)) {
            totalCommission = "0.00";
        }
        if (null == commission || "".equals(commission)) {
            commission = "0.00";
        }
        BigDecimal total = new BigDecimal(totalCommission);
        BigDecimal count = new BigDecimal(commission);
        BigDecimal totalCount = total.add(count);
        totalCount = totalCount.setScale(2, BigDecimal.ROUND_HALF_UP);
        return totalCount.toString();
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
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

    public Integer getServerID() {
        return serverID;
    }

    public void setServerID(Integer serverID) {
        this.serverID = serverID;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getTotalScore() {
        if (null == totalScore || "".equals(totalScore)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(totalScore);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public int getIsAndroid() {
        return isAndroid;
    }

    public void setIsAndroid(int isAndroid) {
        this.isAndroid = isAndroid;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getAdvertPlat() {
        return advertPlat;
    }

    public void setAdvertPlat(String advertPlat) {
        this.advertPlat = advertPlat;
    }

    public String getLastLogonIPAddress() {
        return lastLogonIPAddress;
    }

    public void setLastLogonIPAddress(String lastLogonIPAddress) {
        this.lastLogonIPAddress = lastLogonIPAddress;
    }
}
