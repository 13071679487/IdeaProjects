package com.rifu.springboot.mapper;

import com.rifu.springboot.entity.Category;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  9:41
 */
public interface CategoryMapper {
    List<Category> selectCategorys();

    List<Category> selectByParentId(Integer id);

    Category selectByPrimaryKey(Integer id);

    int insertCategory(Category category);

    int updateCategoryByPrimaryKeySelective(Category category);

    int deleteCategoryByPrimaryKey(Integer id);
}
