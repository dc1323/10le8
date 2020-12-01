package com.ruoyi.games.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private Integer id;
    private String customerValue;
    private int typeID;
    private String typeName;
    private int nullity;
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

    public String getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(String customerValue) {
        this.customerValue = customerValue;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getNullity() {
        return nullity;
    }

    public void setNullity(int nullity) {
        this.nullity = nullity;
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
