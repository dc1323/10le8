package com.ruoyi.games.domain;

import java.math.BigDecimal;

/**
 * @description: 下级返利
 * @author liuyang
 * @date 2020/12/1 16:21
 */
public class CommissionInfo {

    private Integer SonUserID;
    private Integer SonGameID;
    private Integer Loclevel;
    private Double BeCommission;
    private String NickName;
    private Double RebateScale;

    public Integer getSonUserId() {
        return SonUserID;
    }

    public void setSonUserId(Integer SonUserID) {
        this.SonUserID = SonUserID;
    }

    public Integer getSonGameID() {
        return SonGameID;
    }

    public void setSonGameID(Integer SonGameID) {
        this.SonGameID = SonGameID;
    }

    public Integer getLoclevel() {
        return Loclevel;
    }

    public void setLoclevel(Integer Loclevel) {
        this.Loclevel = Loclevel;
    }

    public Double getBeCommission() {
        return BeCommission;
    }

    public void setBeCommission(Double BeCommission) {
        this.BeCommission = BeCommission;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public Double getRebateScale() {
        return RebateScale;
    }

    public void setRebateScale(Double RebateScale) {
        this.RebateScale = RebateScale;
    }


}