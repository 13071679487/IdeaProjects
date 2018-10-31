package com.rifu.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.entity.Product;
import com.rifu.springboot.entity.Record;
import com.rifu.springboot.entity.Repertory;
import com.rifu.springboot.mapper.ProductMapper;
import com.rifu.springboot.mapper.RecordMapper;
import com.rifu.springboot.mapper.RepertoryMapper;
import com.rifu.springboot.service.IRecordService;
import com.rifu.springboot.util.DateFormatUtil;
import com.rifu.springboot.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  19:26
 */
@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    ProductMapper productMapper;

    public ResponseMsg selectRepertorys(int pageNum,int size){
        PageHelper.startPage(pageNum, size,true);
        List<Record> records = recordMapper.selectRecords();
        PageInfo pageInfo = new PageInfo(records);
        List<RecordVo> recordVos=new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            recordVos.add(assemableRecordVo(records.get(i)));
        }
        pageInfo.setList(recordVos);
        return ResponseMsg.createBySuccess(pageInfo);
    }

    private RecordVo assemableRecordVo(Record record){
        if(record == null){
            return null;
        }
        System.out.println(record);
        RecordVo vo = new RecordVo();
        vo.setId(record.getId());
        vo.setAmount(record.getAmount());
        vo.setpNumber(record.getpNumber());
        Product product = productMapper.selectByProductNumber(record.getpNumber());
        if(product!=null){
            vo.setpName(product.getpName());
        }
        if(record.getType() == 0){
            vo.setType("出货");
        }else {
            vo.setType("进货");
        }
        vo.setCreateTime(DateFormatUtil.dateToStr(record.getCreateTime()));
        vo.setUpdateTime(DateFormatUtil.dateToStr(record.getUpdateTime()));
        System.out.println(vo);
        return vo;
    }

}
