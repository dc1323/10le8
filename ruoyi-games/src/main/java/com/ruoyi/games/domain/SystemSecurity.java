package com.ruoyi.games.domain;

import java.util.Date;

public class SystemSecurity {

    private Integer recordID;
    private Date operatingTime;
    private String operatingName;
    private String operatingIP;
    private String operatingAccounts;
    private String remark;
    private String objectAccounts;

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public Date getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getOperatingName() {
        return operatingName;
    }

    public void setOperatingName(String operatingName) {
        this.operatingName = operatingName;
    }

    public String getOperatingIP() {
        return operatingIP;
    }

    public void setOperatingIP(String operatingIP) {
        this.operatingIP = operatingIP;
    }

    public String getOperatingAccounts() {
        return operatingAccounts;
    }

    public void setOperatingAccounts(String operatingAccounts) {
        this.operatingAccounts = operatingAccounts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getObjectAccounts() {
        return objectAccounts;
    }

    public void setObjectAccounts(String objectAccounts) {
        this.objectAccounts = objectAccounts;
    }
}
