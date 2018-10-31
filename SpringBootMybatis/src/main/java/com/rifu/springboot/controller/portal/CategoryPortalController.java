package com.rifu.springboot.controller.portal;

import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Category;
import com.rifu.springboot.entity.Product;
import com.rifu.springboot.service.ICategoryService;
import com.rifu.springboot.vo.CategoryVo;
import com.rifu.springboot.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author Rifu
 * @Date 2018/9/28  18:59
 */
@Controller
@RequestMapping("/portal/cate")
public class CategoryPortalController {

    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value="pn",defaultValue = "1",required = false)int pageNum,
                       @RequestParam(value = "size",defaultValue = "20",required = false)int size){
        ResponseMsg responseMsg = iCategoryService.selectCategorys(pageNum, size);
        Collection<CategoryVo> categoryVos=null;
        if (responseMsg.getData() instanceof PageInfo){
            categoryVos =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("cates",categoryVos);
        return "category/list";
    }


    @GetMapping("/add")
    public String add(){
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(Model model, Category category){
        ResponseMsg resp = iCategoryService.insertCategory(category);
        if(!resp.success()){
            model.addAttribute("errorMsg",resp.getMsg());
        }
        ResponseMsg responseMsg = iCategoryService.selectCategorys(1, 20);
        Collection<CategoryVo> categoryVos=null;
        if (responseMsg.getData() instanceof PageInfo){
            categoryVos =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("cates",categoryVos);
        return "category/list";
    }

}
