package com.ruoyi.games.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/9 14:22
 */
public class HorseLamp {

    private Integer id;
    private String txtBody;
    private Date startTime;
    private Date endTime;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxtBody() {
        return txtBody;
    }

    public void setTxtBody(String txtBody) {
        this.txtBody = txtBody;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
