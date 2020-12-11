package com.ruoyi.games.domain;

/**
 * @description:
 * @date 2020/12/3 0:18
 */
public class OrderInfo {

    private String orderid;
    private String userid;
    private String payamount;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPayamount() {
        return payamount;
    }

    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }
}
