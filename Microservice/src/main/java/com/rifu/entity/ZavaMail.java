package com.rifu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * {
 * "id":1,
 * "title":"advice",
 * "from":"528428122@qq.com",
 * "to":"13071679487@163.com",
 * "createTime":"2018-10-25 13:46:19",
 * "updateTime":"2018-10-25 13:46:19"
 * }
 */
public class ZavaMail implements Serializable {
    private Integer id;

    private String title;

    private String from;

    private String to;

    private Date createTime;

    private Date updateTime;

    private String content;

    public ZavaMail() {
    }

    public ZavaMail(String title, String from, String to, String content) {
        this.title = title;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "ZavaMail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                '}';
    }
}