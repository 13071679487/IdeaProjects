package com.rifu.springboot.controller;

import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Product;
import com.rifu.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rifu
 * @Date 2018/9/22  21:01
 */
@RestController
public class ProductController {

    @Autowired
    IProductService iProductService;

    @GetMapping("/prods")
    public ResponseMsg getProductList(@RequestParam(value=("pn"),defaultValue = "1",required = false)int pageNum,@RequestParam(value = ("size"),defaultValue = "20",required = false)int size){
        return iProductService.getProductList(pageNum, size);
    }

    @GetMapping("/prod/{no}")
    public ResponseMsg getProduct(@PathVariable("no")String  pNumber){
        return iProductService.getProduct(pNumber);
    }

    @PostMapping("/prod")
    public ResponseMsg insertProduct(Product prod){
        return iProductService.insertProduct(prod);
    }

    @PutMapping("/prod")
    public ResponseMsg updateProduct(Product prod){
        return iProductService.updateProduct(prod);
    }

    @DeleteMapping("/prod")
    public ResponseMsg deleteProductByNumber(@RequestParam("no")String pNumber){
        return iProductService.deleteProductByNumber(pNumber);
    }

}
