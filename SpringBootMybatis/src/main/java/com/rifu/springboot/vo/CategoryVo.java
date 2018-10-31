package com.rifu.springboot.vo;


import com.rifu.springboot.entity.Category;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/27  22:44
 */
public class CategoryVo {
    private Integer id;
    private String cName;

    private CategoryVo parentCategory;
    private List<CategoryVo> childrenCategorys;

    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public CategoryVo getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryVo parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<CategoryVo> getChildrenCategorys() {
        return childrenCategorys;
    }

    public void setChildrenCategorys(List<CategoryVo> childrenCategorys) {
        this.childrenCategorys = childrenCategorys;
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
        return "CategoryVo{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", parentCategory=" + parentCategory +
                ", childrenCategorys=" + childrenCategorys +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
