package com.rifu.entity;

/**
 * @Author Rifu
 * @Date 2018/10/17  13:44
 */
public class CustomMsg {

    private Integer id;
    private Integer type;
    private String msg;

    public CustomMsg() {
    }

    public CustomMsg(Integer id, Integer type, String msg) {
        this.id = id;
        this.type = type;
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CustomMsg{" +
                "id=" + id +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
