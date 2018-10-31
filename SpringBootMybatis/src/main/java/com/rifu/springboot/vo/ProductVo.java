package com.rifu.springboot.vo;


/**
 * @Author Rifu
 * @Date 2018/9/27  22:44
 */
public class ProductVo {
    private Integer id;
    private String pNumber;
    private String pName;
    private Integer cId;
    private String cName;
    private int pRepertory;
    private String canDelete;

    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpRepertory() {
        return pRepertory;
    }

    public void setpRepertory(int pRepertory) {
        this.pRepertory = pRepertory;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "id=" + id +
                ", pNumber='" + pNumber + '\'' +
                ", pName='" + pName + '\'' +
                ", cId=" + cId +
                ", cName='" + cName + '\'' +
                ", pRepertory=" + pRepertory +
                ", canDelete='" + canDelete + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
