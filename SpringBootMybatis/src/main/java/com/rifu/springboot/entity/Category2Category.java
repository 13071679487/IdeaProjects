package com.rifu.springboot.entity;

import java.util.Date;

/**
 * @Author Rifu
 * @Date 2018/9/27  23:03
 */
public class Category2Category {
    private Integer id;
    private Integer cId;
    private Integer cParentid;

    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcParentid() {
        return cParentid;
    }

    public void setcParentid(Integer cParentid) {
        this.cParentid = cParentid;
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
