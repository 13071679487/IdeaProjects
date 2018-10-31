package com.rifu.service.impl;

import com.rifu.entity.User;
import com.rifu.mapper.DepartmentMapper;
import com.rifu.mapper.UserMapper;
import com.rifu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author Rifu
 * @Date 2018/10/14  20:32
 */
@CacheConfig(
        cacheNames = {"user"},
        keyGenerator = "customKeyGenerator"
)

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper deptMapper;

    @Cacheable(cacheNames = "user",key = "#id")
    public User selectById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(allEntries = true)
    public void deleteById(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }
}
