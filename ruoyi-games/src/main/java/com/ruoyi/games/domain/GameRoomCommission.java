package com.ruoyi.games.domain;

import java.math.BigDecimal;

/**
 * @description: 获取每日房间抽水
 * @date 2020/12/3 22:21
 */
public class GameRoomCommission {

    private Integer serverID;
    //游戏抽水
    private BigDecimal gameRevenue;
    //代理抽水
    private BigDecimal agentRevenue;

    public Integer getServerID() {
        return serverID;
    }

    public void setServerID(Integer serverID) {
        this.serverID = serverID;
    }

    public BigDecimal getGameRevenue() {
        return gameRevenue;
    }

    public void setGameRevenue(BigDecimal gameRevenue) {
        this.gameRevenue = gameRevenue;
    }

    public BigDecimal getAgentRevenue() {
        return agentRevenue;
    }

    public void setAgentRevenue(BigDecimal agentRevenue) {
        this.agentRevenue = agentRevenue;
    }
}
