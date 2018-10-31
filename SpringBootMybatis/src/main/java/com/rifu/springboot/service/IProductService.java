package com.rifu.springboot.service;

import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Product;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:10
 */
public interface IProductService {
    ResponseMsg getProductList(int pageNum,int size);
    ResponseMsg insertProduct(Product product);
    ResponseMsg getProduct(String pNumber);
    ResponseMsg updateProduct(Product product);
    ResponseMsg deleteProductByNumber(String pNumber);
}
