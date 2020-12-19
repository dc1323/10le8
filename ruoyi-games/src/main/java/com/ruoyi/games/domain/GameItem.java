package com.ruoyi.games.domain;

/**
 * @description:
 * @date 2020/12/19 9:03
 */
public class GameItem {

    private Integer gameID;
    private String gameName;
    private Integer suportType;
    private String dataBaseAddr;
    private String dataBaseName;
    private Integer serverVersion;
    private Integer clientVersion;
    private String serverDLLName;
    private String clientExeName;

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getSuportType() {
        return suportType;
    }

    public void setSuportType(Integer suportType) {
        this.suportType = suportType;
    }

    public String getDataBaseAddr() {
        return dataBaseAddr;
    }

    public void setDataBaseAddr(String dataBaseAddr) {
        this.dataBaseAddr = dataBaseAddr;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public Integer getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(Integer serverVersion) {
        this.serverVersion = serverVersion;
    }

    public Integer getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getServerDLLName() {
        return serverDLLName;
    }

    public void setServerDLLName(String serverDLLName) {
        this.serverDLLName = serverDLLName;
    }

    public String getClientExeName() {
        return clientExeName;
    }

    public void setClientExeName(String clientExeName) {
        this.clientExeName = clientExeName;
    }
}
