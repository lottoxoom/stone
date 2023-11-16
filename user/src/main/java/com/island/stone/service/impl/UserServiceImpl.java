package com.island.stone.service.impl;

import com.island.stone.service.IUserService;
import com.island.stone.entity.User;
import com.island.stone.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户业务实现层
 * @author: LOTTO
 * @create: 2022-01-14 15:29
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

//    @Async
    @Override
    @Cacheable(value = "cache:um:user", key = "#id")
    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int Update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User save(User user) {
        int save = userMapper.save(user);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

}
