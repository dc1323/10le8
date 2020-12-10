package com.ruoyi.games.domain;

import com.ruoyi.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/4 22:11
 */
public class RoomRecord {

    private Integer drawID;
    private Integer userCount;
    private Integer androidCount;
    private String waste;
    private String revenue;
    private Date insertTime;
    private Integer roomNumber;
    private String cellScore;
    private Integer roundCount;
    private String code;
    private String expect;
    private Integer userID;
    private Integer serverID;
    private String serverName;
    private Integer kindID;
    private String kindName;
    private String agentRevenue;
    private String robotsRevenue;
    /**
     * 平台收入
     */
    private String platformRevenue;

    private Date startDate;
    private Date endDate;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Integer getDrawID() {
        return drawID;
    }

    public void setDrawID(Integer drawID) {
        this.drawID = drawID;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getAndroidCount() {
        return androidCount;
    }

    public void setAndroidCount(Integer androidCount) {
        this.androidCount = androidCount;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getRevenue() {
        if (null == revenue || "".equals(revenue)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(revenue);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCellScore() {
        return cellScore;
    }

    public void setCellScore(String cellScore) {
        this.cellScore = cellScore;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getServerID() {
        return serverID;
    }

    public void setServerID(Integer serverID) {
        this.serverID = serverID;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public String getAgentRevenue() {
        if (null == agentRevenue || "".equals(agentRevenue)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(agentRevenue);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setAgentRevenue(String agentRevenue) {
        this.agentRevenue = agentRevenue;
    }

    public String getRobotsRevenue() {
        if (null == robotsRevenue || "".equals(robotsRevenue)) {
            return "0.00";
        }
        BigDecimal temp = new BigDecimal(robotsRevenue);
        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        return temp.toString();
    }

    public void setRobotsRevenue(String robotsRevenue) {
        this.robotsRevenue = robotsRevenue;
    }

    public String getPlatformRevenue() {
        if (StringUtils.isEmpty(agentRevenue)) {
            agentRevenue = "0.00";
        }
        if (StringUtils.isEmpty(revenue)) {
            revenue = "0.00";
        }
        BigDecimal temp1 = new BigDecimal(revenue);
        BigDecimal temp2 = new BigDecimal(agentRevenue);
        BigDecimal temp = temp1.subtract(temp2);
        temp.setScale(2, BigDecimal.ROUND_HALF_UP);
        ;
        return temp.toString();
    }

    public void setPlatformRevenue(String platformRevenue) {
        this.platformRevenue = platformRevenue;
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
