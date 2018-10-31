package com.rifu.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.Constants;
import com.rifu.springboot.common.ResponseCode;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Category;
import com.rifu.springboot.entity.Product;
import com.rifu.springboot.entity.Record;
import com.rifu.springboot.entity.Repertory;
import com.rifu.springboot.mapper.CategoryMapper;
import com.rifu.springboot.mapper.ProductMapper;
import com.rifu.springboot.mapper.RecordMapper;
import com.rifu.springboot.mapper.RepertoryMapper;
import com.rifu.springboot.service.IProductService;
import com.rifu.springboot.util.DateFormatUtil;
import com.rifu.springboot.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:10
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    RepertoryMapper repertoryMapper;

    @Autowired
    RecordMapper recordMapper;

    public ResponseMsg insertProduct(Product product){
        int rowCount = productMapper.insertProduct(product);
        if(rowCount>0){
            return ResponseMsg.createBySuccess(assemableProductVo(product));
        }
        return ResponseMsg.createByError(Constants.ProductCode.OP_FAIL.getCode(),Constants.ProductCode.OP_FAIL.getDesc());
    }

    public ResponseMsg getProductList(int pageNum,int size){
        PageHelper.startPage(pageNum, size);
        List<Product> productList = productMapper.selectAllProduct();
        PageInfo pageInfo = new PageInfo(productList);
        List<ProductVo> productVoList = new ArrayList<>();
        for(int i=0;i<productList.size();i++){
            ProductVo vo = assemableProductVo(productList.get(i));
            productVoList.add(vo);
        }
        pageInfo.setList(productVoList);
        return ResponseMsg.createBySuccess(pageInfo);
    }

    public ResponseMsg getProduct(String pNumber){
        Product product = productMapper.selectByProductNumber(pNumber);
        if(product != null){
            return ResponseMsg.createBySuccess(assemableProductVo(product));
        }
        return ResponseMsg.createByError(Constants.ProductCode.NO_EXIST.getCode(),Constants.ProductCode.NO_EXIST.getDesc());
    }

    public ResponseMsg updateProduct(Product product){
        if(product.getcId() == null){
            return ResponseMsg.createByError(ResponseCode.ILLEGAL_ARG.getCode(),ResponseCode.ILLEGAL_ARG.getDesc());
        }
        Product obj = productMapper.selectByPrimaryKey(product.getId());
        if(obj == null){
            return ResponseMsg.createByError(Constants.ProductCode.NO_EXIST.getCode(),Constants.ProductCode.NO_EXIST.getDesc());
        }
        int rowCount = productMapper.updateProductByPrimaryKeySelective(product);
        if(rowCount>0){
            return ResponseMsg.createBySuccess();
        }
        return ResponseMsg.createByError(Constants.ProductCode.OP_FAIL.getCode(),Constants.ProductCode.OP_FAIL.getDesc());
    }

    public ResponseMsg deleteProductByNumber(String pNumber){
        Product product = productMapper.selectByProductNumber(pNumber);
        if(product == null){
            return ResponseMsg.createByError(Constants.ProductCode.NO_EXIST.getCode(),Constants.ProductCode.NO_EXIST.getDesc());
        }
        List<Record> recordList = recordMapper.selectByProductNumber(pNumber);
        if(recordList.size()>0){
            return ResponseMsg.createByError(Constants.ProductCode.OP_FAIL.getCode(),"存在库存记录，不能删除");
        }
        int rowCount = productMapper.deleteProductByNumber(pNumber);
        if(rowCount>0){
            return ResponseMsg.createBySuccess();
        }
        return ResponseMsg.createByError(Constants.ProductCode.OP_FAIL.getCode(),Constants.ProductCode.OP_FAIL.getDesc());
    }


    private ProductVo assemableProductVo(Product product){
        if(product == null){
            return null;
        }
        ProductVo vo =new ProductVo();
        vo.setId(product.getId());
        vo.setpNumber(product.getpNumber());
        vo.setpName(product.getpName());
        vo.setcId(product.getcId());
        if(product.getcId()!=null){
            Category category = categoryMapper.selectByPrimaryKey(product.getcId());
            if(category !=null){
                vo.setcName(category.getcName());
            }
        }
        if(product.getpNumber()!= null){
            Repertory repertory = repertoryMapper.selectByProductNumber(product.getpNumber());
            if(repertory!=null){
                vo.setpRepertory(repertory.getpAmount());
            }
        }
        List<Record> recordList = recordMapper.selectByProductNumber(product.getpNumber());
        if(recordList.size()>0){
            vo.setCanDelete("否");
        }else {
            vo.setCanDelete("能");
        }
        vo.setCreateTime(DateFormatUtil.dateToStr(product.getCreateTime()));
        vo.setUpdateTime(DateFormatUtil.dateToStr(product.getUpdateTime()));

        return vo;
    }
}
