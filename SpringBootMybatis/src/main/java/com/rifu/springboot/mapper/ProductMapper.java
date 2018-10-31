package com.rifu.springboot.mapper;


import com.rifu.springboot.entity.Product;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/22  20:55
 */
public interface ProductMapper {
    Product selectByPrimaryKey(Integer id);

    Product selectByProductNumber(String pNumber);

    List<Product> selectAllProduct();

    List<Product> selectByCategoryId(List<Integer> cids);

    int insertProduct(Product product);

    int updateProductByPrimaryKeySelective(Product product);

    int deleteProductByPrimaryKey(Integer pid);

    int deleteProductByNumber(String pNumber);

    int deleteProductBatch(List<Integer> pids);

}
