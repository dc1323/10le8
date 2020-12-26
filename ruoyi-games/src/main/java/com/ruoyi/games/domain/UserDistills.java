package com.ruoyi.games.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserDistills {

    private Integer userID;
    private String accounts;
    private String nickName;
    private Integer id;
    private String bankUserName;
    private String bankCardNumber;
    private String money;
    private String beforeGold;
    private Date handleDateTime;
    private Integer result;
    private String memo;
    private Date dateTime;

    private String payAmount;
    private int payAmountCount;

    private String totalPayAmount;
    private String afterGold;

    private String keyWord;
    private Date startDate;
    private Date endDate;
    private String minMoney;
    private String maxMoney;
    private String orderID;
    private Date startHandleDate;
    private Date endHandleDate;

    private Double Amount;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
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

    public String getBeforeGold() {
        if (null == beforeGold || "".equals(beforeGold)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(beforeGold);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setBeforeGold(String beforeGold) {
        this.beforeGold = beforeGold;
    }

    public Date getHandleDateTime() {
        return handleDateTime;
    }

    public void setHandleDateTime(Date handleDateTime) {
        this.handleDateTime = handleDateTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public int getPayAmountCount() {
        return payAmountCount;
    }

    public void setPayAmountCount(int payAmountCount) {
        this.payAmountCount = payAmountCount;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getAfterGold() {
        if (null == beforeGold || "".equals(beforeGold)) {
            beforeGold = "0.00";
        }
        if (null == money || "".equals(money)) {
            money = "0.00";
        }
        BigDecimal temp1 = new BigDecimal(beforeGold);
        BigDecimal temp2 = new BigDecimal(money);
        BigDecimal temp = new BigDecimal("0");
        if(result == 1){
            temp = temp1.add(temp2);
        }else{
            temp = temp1.subtract(temp2);
        }
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setAfterGold(String afterGold) {
        this.afterGold = afterGold;
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

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getStartHandleDate() {
        return startHandleDate;
    }

    public void setStartHandleDate(Date startHandleDate) {
        this.startHandleDate = startHandleDate;
    }

    public Date getEndHandleDate() {
        return endHandleDate;
    }

    public void setEndHandleDate(Date endHandleDate) {
        this.endHandleDate = endHandleDate;
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

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }
}
