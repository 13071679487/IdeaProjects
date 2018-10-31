package com.rifu.springboot.entity;

import java.util.Date;

/**
 * @Author Rifu
 * @Date 2018/9/27  23:12
 */
public class Repertory {
    private String pNumber;
    private int pAmount;

    private Date createTime;
    private Date updateTime;

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public int getpAmount() {
        return pAmount;
    }

    public void setpAmount(int pAmount) {
        this.pAmount = pAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
