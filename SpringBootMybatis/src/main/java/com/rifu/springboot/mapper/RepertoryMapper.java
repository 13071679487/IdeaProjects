package com.rifu.springboot.mapper;

import com.rifu.springboot.entity.Repertory;

import java.util.List;

/**
 * @Author Rifu
 * @Date 2018/9/28  10:00
 */
public interface RepertoryMapper {
    Repertory selectByProductNumber(String pNumber);

    List<Repertory> selectRepertorys();

    int insertRepertory(Repertory repertory);

    int updateRepertorySelective(Repertory repertory);

}
