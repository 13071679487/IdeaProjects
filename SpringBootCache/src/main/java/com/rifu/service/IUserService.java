package com.rifu.service;

import com.rifu.entity.User;

/**
 * @Author Rifu
 * @Date 2018/10/14  20:32
 */
public interface IUserService {
    User selectById(Integer id);
    void deleteById(Integer id);
}
