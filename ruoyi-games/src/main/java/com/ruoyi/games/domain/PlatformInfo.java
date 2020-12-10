package com.ruoyi.games.domain;

import com.ruoyi.common.utils.StringUtils;

/**
 * @description:
 * @date 2020/12/5 11:26
 */
public class PlatformInfo {

    private String todayChargeMoney;// DECIMAL						-- 今日充值额
    private String todayDistlls; //DECIMAL							-- 今日提款
    private String userTotalMoney; //DECIMAL							-- 用户总余额
    private String onlineUsers; //INT								-- 当前在线人数
    private String todayReg; //INT									-- 今日新增玩家人数
    private String regTotal; //INT									-- 注册玩家总数
    private String systemRevenue; //DECIMAL							-- 系统抽水总量
    private String agentRevenue; //DECIMAL							-- 代理抽水总量
    private String robotTotal; //INT									-- 机器人总数
    private String onilneRobotTotal; //INT							-- 在线机器人总数
    private String robotTotalBalance; //INT							-- 机器人总余额
    private String totalProfitAndLoss; //INT							-- 总盈亏
    private String totalCharge; //INT								-- 总充值

    public String getTodayChargeMoney() {
        if(StringUtils.isEmpty(todayChargeMoney)){
            return "0.00";
        }
        return todayChargeMoney;
    }

    public void setTodayChargeMoney(String todayChargeMoney) {
        this.todayChargeMoney = todayChargeMoney;
    }

    public String getTodayDistlls() {
        if(StringUtils.isEmpty(todayDistlls)){
            return "0.00";
        }
        return todayDistlls;
    }

    public void setTodayDistlls(String todayDistlls) {
        this.todayDistlls = todayDistlls;
    }

    public String getUserTotalMoney() {
        if(StringUtils.isEmpty(userTotalMoney)){
            return "0.00";
        }
        return userTotalMoney;
    }

    public void setUserTotalMoney(String userTotalMoney) {
        this.userTotalMoney = userTotalMoney;
    }

    public String getOnlineUsers() {
        if(StringUtils.isEmpty(onlineUsers)){
            return "0";
        }
        return onlineUsers;
    }

    public void setOnlineUsers(String onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public String getTodayReg() {
        if(StringUtils.isEmpty(todayReg)){
            return "0";
        }
        return todayReg;
    }

    public void setTodayReg(String todayReg) {
        this.todayReg = todayReg;
    }

    public String getRegTotal() {
        if(StringUtils.isEmpty(regTotal)){
            return "0";
        }
        return regTotal;
    }

    public void setRegTotal(String regTotal) {
        this.regTotal = regTotal;
    }

    public String getSystemRevenue() {
        if(StringUtils.isEmpty(systemRevenue)){
            return "0.00";
        }
        return systemRevenue;
    }

    public void setSystemRevenue(String systemRevenue) {
        this.systemRevenue = systemRevenue;
    }

    public String getAgentRevenue() {
        if(StringUtils.isEmpty(agentRevenue)){
            return "0.00";
        }
        return agentRevenue;
    }

    public void setAgentRevenue(String agentRevenue) {
        this.agentRevenue = agentRevenue;
    }

    public String getRobotTotal() {
        if(StringUtils.isEmpty(robotTotal)){
            return "0";
        }
        return robotTotal;
    }

    public void setRobotTotal(String robotTotal) {
        this.robotTotal = robotTotal;
    }

    public String getOnilneRobotTotal() {
        if(StringUtils.isEmpty(onilneRobotTotal)){
            return "0";
        }
        return onilneRobotTotal;
    }

    public void setOnilneRobotTotal(String onilneRobotTotal) {
        this.onilneRobotTotal = onilneRobotTotal;
    }

    public String getRobotTotalBalance() {
        if(StringUtils.isEmpty(robotTotalBalance)){
            return "0.00";
        }
        return robotTotalBalance;
    }

    public void setRobotTotalBalance(String robotTotalBalance) {
        this.robotTotalBalance = robotTotalBalance;
    }

    public String getTotalProfitAndLoss() {
        if(StringUtils.isEmpty(totalProfitAndLoss)){
            return "0.00";
        }
        return totalProfitAndLoss;
    }

    public void setTotalProfitAndLoss(String totalProfitAndLoss) {
        this.totalProfitAndLoss = totalProfitAndLoss;
    }

    public String getTotalCharge() {
        if(StringUtils.isEmpty(totalCharge)){
            return "0.00";
        }
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }
}
