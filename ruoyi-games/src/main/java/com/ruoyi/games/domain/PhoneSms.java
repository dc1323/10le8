package com.ruoyi.games.domain;

import java.util.Date;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/7 20:28
 */
public class PhoneSms {

    private Integer id;
    private String smsCode;
    private Integer userID;
    private short typeID;
    private short isUse;
    private Date insertTime;
    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public short getTypeID() {
        return typeID;
    }

    public void setTypeID(short typeID) {
        this.typeID = typeID;
    }

    public short getIsUse() {
        return isUse;
    }

    public void setIsUse(short isUse) {
        this.isUse = isUse;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
