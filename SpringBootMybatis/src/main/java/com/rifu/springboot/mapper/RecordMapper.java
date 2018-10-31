package com.rifu.springboot.mapper;

import com.rifu.springboot.entity.Record;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  10:53
 */
public interface RecordMapper {
    int insertRecord(Record record);

    List<Record> selectByProductNumber(String pNumber);

    List<Record> selectRecords();

}
