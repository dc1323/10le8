package com.ruoyi.games.domain;

/**
 * @description:
 * @date 2020/12/12 9:32
 */
public class GameRecord {

    private String score;
    private String curMonth;
    private Integer kindID;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCurMonth() {
        return curMonth;
    }

    public void setCurMonth(String curMonth) {
        this.curMonth = curMonth;
    }

    public Integer getKindID() {
        return kindID;
    }

    public void setKindID(Integer kindID) {
        this.kindID = kindID;
    }
}
