package com.ruoyi.games.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/8 19:03
 */
public class OnLineOrder {

    private Integer orderID;
    private Date applyDate;
    private String payAmount;
    private Integer orderStatus;
    private String strOrderStatus;
    private String orderType;
    private Integer shareID;
    private String orderChannel;

    private Map<String,Object> params;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStrOrderStatus() {
        if(orderStatus == 2){
            return "成功";
        }else {
            return "失败";
        }
    }

    public void setStrOrderStatus(String strOrderStatus) {
        this.strOrderStatus = strOrderStatus;
    }

    public String getOrderType() {
        return this.getRechargeByShareID(shareID);
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getShareID() {
        return shareID;
    }

    public void setShareID(Integer shareID) {
        this.shareID = shareID;
    }

    public String getOrderChannel() {
        if(Objects.equals(99998,shareID)){
            return "线下充值";
        }else{
            return "在线充值";
        }
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    private String getRechargeByShareID(Integer ShareID)
    {
        String strShareID = "手工充值";
        switch (ShareID)
        {
            case 201:
                strShareID = "支付宝充值";
                break;
            case 202:
                strShareID = "微信充值";
                break;
            case 99998:
                strShareID = "手工充值";
                break;
        }

        return strShareID;
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
