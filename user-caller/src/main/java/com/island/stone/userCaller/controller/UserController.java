package com.island.stone.userCaller.controller;

import com.island.stone.userCaller.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户控制层
 * @author: LOTTO
 * @create: 2022-01-20 18:03
 **/
@RestController
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/user")
    public String getUser(){
        return userFeign.getUser();
    }

}
