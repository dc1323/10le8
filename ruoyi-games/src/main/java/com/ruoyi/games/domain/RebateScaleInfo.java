package com.ruoyi.games.domain;

import java.math.BigDecimal;

/**
 * @description: 抽水返利
 * @author liuyang
 * @date 2020/12/1 16:21
 */
public class RebateScaleInfo {

    private Integer id;
    private BigDecimal rebateScale;
    private int scaleLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRebateScale() {
        return rebateScale;
    }

    public void setRebateScale(BigDecimal rebateScale) {
        this.rebateScale = rebateScale;
    }

    public int getScaleLevel() {
        return scaleLevel;
    }

    public void setScaleLevel(int scaleLevel) {
        this.scaleLevel = scaleLevel;
    }
}
