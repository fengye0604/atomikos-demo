package com.xy.model;

/**
 * @description:
 * @projectName:atomikos-demo
 * @see:com.xy.model
 * @author:yanggaoli
 * @createTime:2021/4/24 14:50
 * @version:1.0
 */
public class Account {
    private String klId;
    private String money;
    private String userId;

    public String getKlId() {
        return klId;
    }

    public void setKlId(String klId) {
        this.klId = klId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
