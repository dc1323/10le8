package com.ruoyi.games.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameRoomInfo {

    private Integer serverID;

    private String serverName;

    private Integer kindID;
    private String kindName;

    private int nodeID;

    private int sortID;

    private Integer gameID;

    private int tableCount;

    private int serverKind;

    private int serverType;

    private int serverPort;

    private String serverPasswd;

    private String dataBaseName;

    private String dataBaseAddr;

    private String cellScore;

    private float revenueRatio;

    private long serviceScore;

    private long restrictScore;

    private long minTableScore;

    private long minEnterScore;

    private long maxEnterScore;

    private int minEnterMember;

    private int maxEnterMember;

    private int maxPlayer;

    private int serverRule;

    private int distributeRule;

    private int minDistributeUser;

    private int distributeTimeSpace;

    private int distributeDrawCount;

    private int minPartakeGameUser;

    private int maxPartakeGameUser;

    private int attachUserRight;

    private String serviceMachine;

    private String customRule;

    private String nullity;

    private String nullityStatus;

    private String serverNote;

    private Date createDateTime;

    private Date modifyDateTime;

    private String enterPassword;

    private String serverLevel;

    private String attachFiled;

    private int userCount;
    private int addCounts;
    private int androidCount;
    //游戏抽水
    private BigDecimal gameRevenue;
    //代理抽水
    private BigDecimal agentRevenue;
    private BigDecimal platformRevenue;

    private int kindName123;
    private int KindName124;
    //是否在线
    private int isOnline;

    private int cbDWZWin;
    private int cbXWZWin;
    private int cbWZWin;
    private int cbDLLost;
    private int cbXLLost;

    private int cbFreeTime;
    private int cbBetTime;
    private int cbEndTime;

    private String cmd;

    /// <summary>
    /// 九位每位赢
    /// </summary>
    private int cbDWZWin2Nine;
    /// <summary>
    /// 八位每位赢
    /// </summary>
    private int cbWZWin2Eight;
    /// <summary>
    /// 八位每位输
    /// </summary>
    private int cbXLLost2Eigbht;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getCbDWZWin() {
        return cbDWZWin;
    }

    public void setCbDWZWin(int cbDWZWin) {
        this.cbDWZWin = cbDWZWin;
    }

    public int getCbXWZWin() {
        return cbXWZWin;
    }

    public void setCbXWZWin(int cbXWZWin) {
        this.cbXWZWin = cbXWZWin;
    }

    public int getCbWZWin() {
        return cbWZWin;
    }

    public void setCbWZWin(int cbWZWin) {
        this.cbWZWin = cbWZWin;
    }

    public int getCbDLLost() {
        return cbDLLost;
    }

    public void setCbDLLost(int cbDLLost) {
        this.cbDLLost = cbDLLost;
    }

    public int getCbXLLost() {
        return cbXLLost;
    }

    public void setCbXLLost(int cbXLLost) {
        this.cbXLLost = cbXLLost;
    }

    public int getCbFreeTime() {
        return cbFreeTime;
    }

    public void setCbFreeTime(int cbFreeTime) {
        this.cbFreeTime = cbFreeTime;
    }

    public int getCbBetTime() {
        return cbBetTime;
    }

    public void setCbBetTime(int cbBetTime) {
        this.cbBetTime = cbBetTime;
    }

    public int getCbEndTime() {
        return cbEndTime;
    }

    public void setCbEndTime(int cbEndTime) {
        this.cbEndTime = cbEndTime;
    }

    public int getCbDWZWin2Nine() {
        return cbDWZWin2Nine;
    }

    public void setCbDWZWin2Nine(int cbDWZWin2Nine) {
        this.cbDWZWin2Nine = cbDWZWin2Nine;
    }

    public int getCbWZWin2Eight() {
        return cbWZWin2Eight;
    }

    public void setCbWZWin2Eight(int cbWZWin2Eight) {
        this.cbWZWin2Eight = cbWZWin2Eight;
    }

    public int getCbXLLost2Eigbht() {
        return cbXLLost2Eigbht;
    }

    public void setCbXLLost2Eigbht(int cbXLLost2Eigbht) {
        this.cbXLLost2Eigbht = cbXLLost2Eigbht;
    }

    public String getNullityStatus() {
        return nullityStatus;
    }

    public void setNullityStatus(String nullityStatus) {
        this.nullityStatus = nullityStatus;
    }

    /**
     * 请求参数
     */
    private Map<String, Object> params;

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

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public int getTableCount() {
        return tableCount;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    public int getServerKind() {
        return serverKind;
    }

    public void setServerKind(int serverKind) {
        this.serverKind = serverKind;
    }

    public int getServerType() {
        return serverType;
    }

    public void setServerType(int serverType) {
        this.serverType = serverType;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPasswd() {
        return serverPasswd;
    }

    public void setServerPasswd(String serverPasswd) {
        this.serverPasswd = serverPasswd;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDataBaseAddr() {
        return dataBaseAddr;
    }

    public void setDataBaseAddr(String dataBaseAddr) {
        this.dataBaseAddr = dataBaseAddr;
    }

    public String getCellScore() {
        return cellScore;
    }

    public void setCellScore(String cellScore) {
        this.cellScore = cellScore;
    }

    public float getRevenueRatio() {
        return revenueRatio;
    }

    public void setRevenueRatio(float revenueRatio) {
        this.revenueRatio = revenueRatio;
    }

    public long getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(long serviceScore) {
        this.serviceScore = serviceScore;
    }

    public long getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(long restrictScore) {
        this.restrictScore = restrictScore;
    }

    public long getMinTableScore() {
        return minTableScore;
    }

    public void setMinTableScore(long minTableScore) {
        this.minTableScore = minTableScore;
    }

    public long getMinEnterScore() {
        return minEnterScore;
    }

    public void setMinEnterScore(long minEnterScore) {
        this.minEnterScore = minEnterScore;
    }

    public long getMaxEnterScore() {
        return maxEnterScore;
    }

    public void setMaxEnterScore(long maxEnterScore) {
        this.maxEnterScore = maxEnterScore;
    }

    public int getMinEnterMember() {
        return minEnterMember;
    }

    public void setMinEnterMember(int minEnterMember) {
        this.minEnterMember = minEnterMember;
    }

    public int getMaxEnterMember() {
        return maxEnterMember;
    }

    public void setMaxEnterMember(int maxEnterMember) {
        this.maxEnterMember = maxEnterMember;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public int getServerRule() {
        return serverRule;
    }

    public void setServerRule(int serverRule) {
        this.serverRule = serverRule;
    }

    public int getDistributeRule() {
        return distributeRule;
    }

    public void setDistributeRule(int distributeRule) {
        this.distributeRule = distributeRule;
    }

    public int getMinDistributeUser() {
        return minDistributeUser;
    }

    public void setMinDistributeUser(int minDistributeUser) {
        this.minDistributeUser = minDistributeUser;
    }

    public int getDistributeTimeSpace() {
        return distributeTimeSpace;
    }

    public void setDistributeTimeSpace(int distributeTimeSpace) {
        this.distributeTimeSpace = distributeTimeSpace;
    }

    public int getDistributeDrawCount() {
        return distributeDrawCount;
    }

    public void setDistributeDrawCount(int distributeDrawCount) {
        this.distributeDrawCount = distributeDrawCount;
    }

    public int getMinPartakeGameUser() {
        return minPartakeGameUser;
    }

    public void setMinPartakeGameUser(int minPartakeGameUser) {
        this.minPartakeGameUser = minPartakeGameUser;
    }

    public int getMaxPartakeGameUser() {
        return maxPartakeGameUser;
    }

    public void setMaxPartakeGameUser(int maxPartakeGameUser) {
        this.maxPartakeGameUser = maxPartakeGameUser;
    }

    public int getAttachUserRight() {
        return attachUserRight;
    }

    public void setAttachUserRight(int attachUserRight) {
        this.attachUserRight = attachUserRight;
    }

    public String getServiceMachine() {
        return serviceMachine;
    }

    public void setServiceMachine(String serviceMachine) {
        this.serviceMachine = serviceMachine;
    }

    public String getCustomRule() {
        return customRule;
    }

    public void setCustomRule(String customRule) {
        this.customRule = customRule;
    }

    public String getNullity() {
        return nullity;
    }

    public void setNullity(String nullity) {
        this.nullity = nullity;
    }

    public String getServerNote() {
        return serverNote;
    }

    public void setServerNote(String serverNote) {
        this.serverNote = serverNote;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    public String getEnterPassword() {
        return enterPassword;
    }

    public void setEnterPassword(String enterPassword) {
        this.enterPassword = enterPassword;
    }

    public String getServerLevel() {
        return serverLevel;
    }

    public void setServerLevel(String serverLevel) {
        this.serverLevel = serverLevel;
    }

    public String getAttachFiled() {
        return attachFiled;
    }

    public void setAttachFiled(String attachFiled) {
        this.attachFiled = attachFiled;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getAddCounts() {
        return addCounts;
    }

    public void setAddCounts(int addCounts) {
        this.addCounts = addCounts;
    }

    public int getAndroidCount() {
        return androidCount;
    }

    public void setAndroidCount(int androidCount) {
        this.androidCount = androidCount;
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

    public BigDecimal getPlatformRevenue() {
        if (null == gameRevenue) {
            gameRevenue = new BigDecimal(0);
        }
        if (null == agentRevenue) {
            agentRevenue = new BigDecimal(0);
        }
        return gameRevenue.subtract(agentRevenue);
    }

    public void setPlatformRevenue(BigDecimal platformRevenue) {
        this.platformRevenue = platformRevenue;
    }

    public int getKindName123() {
        return kindName123;
    }

    public void setKindName123(int kindName123) {
        this.kindName123 = kindName123;
    }

    public int getKindName124() {
        return KindName124;
    }

    public void setKindName124(int kindName124) {
        KindName124 = kindName124;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
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
