package com.rifu.mapper;

import com.rifu.entity.ZavaMail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ZavaMailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZavaMail record);

    int insertSelective(ZavaMail record);

    ZavaMail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZavaMail record);

    int updateByPrimaryKeyWithBLOBs(ZavaMail record);

    int updateByPrimaryKey(ZavaMail record);

    int hasSendMail(@Param("to") String to,@Param("deadline") Date time);
}