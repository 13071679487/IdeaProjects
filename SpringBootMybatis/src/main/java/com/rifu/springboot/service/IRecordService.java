package com.rifu.springboot.service;

import com.rifu.springboot.common.ResponseMsg;

/**
 * @Author Rifu
 * @Date 2018/9/28  19:25
 */
public interface IRecordService {
    ResponseMsg selectRepertorys(int pageNum, int size);
}
