package com.rifu.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.Constants;
import com.rifu.springboot.common.ResponseCode;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Category;
import com.rifu.springboot.mapper.Category2CategoryMapper;
import com.rifu.springboot.mapper.CategoryMapper;
import com.rifu.springboot.service.ICategoryService;
import com.rifu.springboot.util.DateFormatUtil;
import com.rifu.springboot.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  11:13
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    Category2CategoryMapper category2CategoryMapper;

    public ResponseMsg selectCategorys(int pageNum,int size){
        PageHelper.startPage(pageNum, size);
        List<Category> categories = categoryMapper.selectCategorys();
        PageInfo pageInfo = new PageInfo(categories);
        List<CategoryVo> categoryVos=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            CategoryVo vo = new CategoryVo();
            assemableCategoryVo(categories.get(i),vo,false,false);
            categoryVos.add(vo);
        }
        pageInfo.setList(categoryVos);
        return ResponseMsg.createBySuccess(pageInfo);
    }

    public ResponseMsg selectByPrimaryKey(Integer id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category == null){
            return ResponseMsg.createByError(Constants.CategoryCode.NO_EXIST.getCode(),Constants.CategoryCode.NO_EXIST.getDesc());
        }
        CategoryVo vo = new CategoryVo();
        assemableCategoryVo(category,vo,false,false);
        return ResponseMsg.createBySuccess(vo);
    }

    public ResponseMsg selectAllChildrenCategory(Integer id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category == null){
            return ResponseMsg.createByError(Constants.CategoryCode.NO_EXIST.getCode(),Constants.CategoryCode.NO_EXIST.getDesc());
        }
        CategoryVo vo = new CategoryVo();
        assemableCategoryVo(category,vo,false,true);
        return ResponseMsg.createBySuccess(vo);
    }

    public ResponseMsg insertCategory(Category category){
        if(category.getcName() == null){
            return ResponseMsg.createByError(ResponseCode.ILLEGAL_ARG.getCode(),ResponseCode.ILLEGAL_ARG.getDesc());
        }
        int rowCount = categoryMapper.insertCategory(category);
        if(rowCount>0){
            return ResponseMsg.createBySuccess(category);
        }
        return ResponseMsg.createByError();
    }

    /**
     *
     * @param category
     * @param vo
     * @param matchParent    是否获取父节点的信息
     * @param matchChildren  是否获取子节点的信息
     * @return
     */
    private CategoryVo assemableCategoryVo(Category category,CategoryVo vo,boolean matchParent,boolean matchChildren){
        if(category == null){
            return null;
        }
        vo.setId(category.getId());
        vo.setcName(category.getcName());
        vo.setCreateTime(DateFormatUtil.dateToStr(category.getCreateTime()));
        vo.setUpdateTime(DateFormatUtil.dateToStr(category.getUpdateTime()));
        if(matchParent){
            Category parent = category2CategoryMapper.selectParentCategoryByCid(category.getId());
            if(parent != null){
                CategoryVo vo1 = new CategoryVo();
                assemableCategoryVo(parent,vo1,matchParent,matchChildren);
                vo.setParentCategory(vo1);
            }
        }
        if(matchChildren){
            List<Category> categories = category2CategoryMapper.selectChildrenCategoryByCid(category.getId());
            if(categories==null||categories.size()==0){
                return null;
            }
            List<CategoryVo> categoryVoList = new ArrayList<>();
            for(int i=0;i<categories.size();i++){
                CategoryVo vo1 = new CategoryVo();
                assemableCategoryVo(categories.get(i),vo1,matchParent,matchChildren);
                categoryVoList.add(vo1);
            }
            vo.setChildrenCategorys(categoryVoList);
        }
        return vo;
    }
}
