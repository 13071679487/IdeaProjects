package com.rifu.springboot.service;

import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Category;

/**
 * @Author Rifu
 * @Date 2018/9/28  11:12
 */
public interface ICategoryService {
    ResponseMsg selectCategorys(int pageNum, int size);
    ResponseMsg selectByPrimaryKey(Integer id);
    ResponseMsg selectAllChildrenCategory(Integer id);

    ResponseMsg insertCategory(Category category);
}
