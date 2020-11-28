package com.ruoyi.games.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecordAchievementDetail {

    private Integer userID;
    private Integer gameID;
    private String nickName;
    private String orderID;
    private String kindName;
    private Integer roomNumber;
    private String cellScore;
    private String tCommission;
    private String beforeScore;
    private Date insertTime;
    private String money;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCellScore() {
        if (null == cellScore || "".equals(cellScore)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(cellScore);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setCellScore(String cellScore) {
        this.cellScore = cellScore;
    }

    public String gettCommission() {
        if (null == tCommission || "".equals(tCommission)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(tCommission);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void settCommission(String tCommission) {
        this.tCommission = tCommission;
    }

    public String getBeforeScore() {
        if (null == beforeScore || "".equals(beforeScore)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(beforeScore);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setBeforeScore(String beforeScore) {
        this.beforeScore = beforeScore;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getMoney() {
        if (null == beforeScore || "".equals(beforeScore)) {
            beforeScore = "0.00";
        }
        if (null == tCommission || "".equals(tCommission)) {
            tCommission = "0.00";
        }
        BigDecimal temp1 = new BigDecimal(beforeScore);
        BigDecimal temp2 = new BigDecimal(tCommission);
        BigDecimal temp = temp1.add(temp2);
        temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setMoney(String money) {
        this.money = money;
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
