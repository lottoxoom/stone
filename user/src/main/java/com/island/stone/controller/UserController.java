package com.island.stone.controller;

import com.island.stone.service.IUserService;
import com.island.stone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户控制层
 * @author: LOTTO
 * @create: 2022-01-14 15:40
 **/
@RestController
@RequestMapping(value = "/stone")
public class UserController {

   @Autowired
   private IUserService userService;

    //获取用户所有信息
    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable("id") int id) {
        return userService.getUserInfo(id).toString();
    }

    //通过用户id删除用户
    @DeleteMapping("/delete")
    public String delete(int id) {
        int result = userService.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    //根据用户id更新用户信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userService.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
    //插入新用户
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userService.save(user);
    }


    //打印所有用户信息
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<User> ListUser() {
        return userService.selectAll();
    }

}