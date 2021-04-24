package com.xy.model;

import java.util.Date;

/**
 * @description:
 * @projectName:atomikos-demo
 * @see:com.xy.model
 * @author:yanggaoli
 * @createTime:2021/4/24 14:51
 * @version:1.0
 */
public class User {

    private String id;
    private String username;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
