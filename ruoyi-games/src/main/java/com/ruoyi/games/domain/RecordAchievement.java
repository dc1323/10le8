package com.ruoyi.games.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 佣金明细表
 */
public class RecordAchievement {

    private Integer detailID;
    private Integer userID;
    private Integer gameID;
    private String accounts;
    private String nickName;
    private String registerMobile;
    private String compellation;
    private String score;
    private String insureScore;
    private String payAmount;
    private Integer shareID;
    private String orderID;
    private Date applyDate;

    private String keyWord;
    private Date startDate;
    private Date endDate;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Integer getDetailID() {
        return detailID;
    }

    public void setDetailID(Integer detailID) {
        this.detailID = detailID;
    }

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

    public String getPayAmount() {
        if (null == payAmount || "".equals(payAmount)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(payAmount);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getShareID() {
        return shareID;
    }

    public void setShareID(Integer shareID) {
        this.shareID = shareID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
