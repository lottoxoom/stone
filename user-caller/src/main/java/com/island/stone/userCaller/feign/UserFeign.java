package com.island.stone.userCaller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 获取用户信息(调用者的接口方法是被调用者方法的声明 ,映射路径与被调用者方法的映射路径相同)
 * @author: LOTTO
 * @create: 2022-01-20 17:59
 **/
@FeignClient("user")//调用的服务名称
public interface UserFeign {

    @GetMapping("/stone/getUser/3")
    String getUser();

}
