package com.ruoyi.games.domain;

import java.util.Date;

/**
 * @description:
 * @date 2020/12/11 15:45
 */
public class ShareDetailInfo {

    private Date collectDate;
    private String payAmount;
    private String beforeGold;
    private String gold;
    private String status;

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getBeforeGold() {
        return beforeGold;
    }

    public void setBeforeGold(String beforeGold) {
        this.beforeGold = beforeGold;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getStatus() {
        return "成功";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
