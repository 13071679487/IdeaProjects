package com.rifu.springboot.mapper;

import com.rifu.springboot.entity.Category;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  12:21
 */
public interface Category2CategoryMapper {
    Category selectParentCategoryByCid(Integer cid);
    List<Category> selectChildrenCategoryByCid(Integer cid);
}
