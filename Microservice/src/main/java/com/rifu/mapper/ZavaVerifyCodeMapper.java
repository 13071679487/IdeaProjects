package com.rifu.mapper;

import com.rifu.entity.ZavaVerifyCode;

public interface ZavaVerifyCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZavaVerifyCode record);

    int insertSelective(ZavaVerifyCode record);

    ZavaVerifyCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZavaVerifyCode record);

    int updateByPrimaryKey(ZavaVerifyCode record);
}