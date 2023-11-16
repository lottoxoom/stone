package com.island.stone.service;

import com.island.stone.entity.User;
import java.util.List;

/**
 * @description: 用户业务层
 * @author: LOTTO
 * @create: 2022-01-14 15:28
 **/
public interface IUserService {

    User getUserInfo(int id);

    int deleteById(int id);

    int Update(User user);

    User save(User user);

    List<User> selectAll() ;

}
