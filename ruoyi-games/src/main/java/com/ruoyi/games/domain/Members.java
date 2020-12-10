package com.ruoyi.games.domain;

import java.util.Date;

/**
 * @description:
 * @date 2020/12/10 22:51
 */
public class Members {

    private Integer userID;
    private Integer gameID;
    private String nickName;
    private String registerDate;
    private int loclevel;
    private String loclevelDes;
    private String members;
    private String commission;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getLoclevel() {
        return loclevel;
    }

    public void setLoclevel(int loclevel) {
        this.loclevel = loclevel;
    }

    public String getLoclevelDes() {
        if(loclevel == 1){
            return "8%";
        }else{
            return "5%";
        }
    }

    public void setLoclevelDes(String loclevelDes) {
        this.loclevelDes = loclevelDes;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
