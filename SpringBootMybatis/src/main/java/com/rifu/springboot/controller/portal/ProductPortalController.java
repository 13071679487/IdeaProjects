package com.rifu.springboot.controller.portal;

import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Product;
import com.rifu.springboot.service.ICategoryService;
import com.rifu.springboot.service.IProductService;
import com.rifu.springboot.vo.CategoryVo;
import com.rifu.springboot.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  17:02
 */
@Controller
@RequestMapping("/portal/prod")
public class ProductPortalController {

    @Autowired
    IProductService iProductService;
    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="pn",defaultValue = "1",required = false)int pageNum,@RequestParam(value = "size",defaultValue = "20",required = false)int size){
        ResponseMsg responseMsg = iProductService.getProductList(pageNum, size);
        Collection<ProductVo> productVos=null;
        if (responseMsg.getData() instanceof PageInfo){
            productVos =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("prods",productVos);
        return "product/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        ResponseMsg responseMsg = iCategoryService.selectCategorys(1, Integer.MAX_VALUE);
        Collection<CategoryVo> list=null;
        if (responseMsg.getData() instanceof PageInfo){
            list =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("cates",list);
        return "product/add";
    }

    @PostMapping("/saveOrUpdate")
    public String addProduct(Model model,Product product){
        ResponseMsg responseMsg = iProductService.insertProduct(product);
        if(!responseMsg.success()){
            model.addAttribute("errorMsg",responseMsg.getMsg());
            return "product/add";
        }
        return "redirect:/portal/prod/list";
    }

    @GetMapping("/update/{no}")
    public String update(Model model,@PathVariable("no")String pNumber){
        ResponseMsg resp = iProductService.getProduct(pNumber);
        if(resp.success()){
            model.addAttribute("prod",resp.getData());
        }
        ResponseMsg responseMsg = iCategoryService.selectCategorys(1, Integer.MAX_VALUE);
        Collection<CategoryVo> list=null;
        if (responseMsg.getData() instanceof PageInfo){
            list =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("cates",list);
        return "product/add";
    }

    @PutMapping("/saveOrUpdate")
    public String updateProduct(Model model,Product product){
        ResponseMsg responseMsg = iProductService.updateProduct(product);
        if(!responseMsg.success()){
            model.addAttribute("errorMsg",responseMsg.getMsg());
            return "product/add";
        }
        return "redirect:/portal/prod/list";
    }

    @DeleteMapping("/delete/{no}")
    public String delete(Model model, @PathVariable("no")String pNumber){
        ResponseMsg resp = iProductService.deleteProductByNumber(pNumber);
        if(!resp.success()){
            model.addAttribute("errorMsg",resp.getMsg());
        }
        ResponseMsg responseMsg = iProductService.getProductList(1, 8);
        Collection<ProductVo> productVos=null;
        if (responseMsg.getData() instanceof PageInfo){
            productVos =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("prods",productVos);
        return "product/list";
    }
}
