package com.rifu.springboot.controller;

import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rifu
 * @Date 2018/9/28  11:11
 */
@RestController
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("/cate/{id}")
    public ResponseMsg selectCategoryByPrimaryKey(@PathVariable("id")Integer id){
        return iCategoryService.selectByPrimaryKey(id);
    }

    @GetMapping("/cates")
    public ResponseMsg selectCategorys(@RequestParam(value=("pn"),defaultValue = "1",required = false)int pageNum,@RequestParam(value = ("size"),defaultValue = "20",required = false)int size){
        return iCategoryService.selectCategorys(pageNum, size);
    }

    @GetMapping("/cate")
    public ResponseMsg getCategoryTree(@RequestParam("id")Integer id){
        return iCategoryService.selectAllChildrenCategory(id);
    }
}
