package com.island.stone.gateway.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description: 用户信息验证
 * @author: LOTTO
 * @create: 2022-02-10 17:30
 **/
@Service
public class PeopleDetailService implements UserDetailsService {

    private static final String USERNAME = "pd";
    private static final String PASSWORD = "aaa";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户信息

        if(Objects.isNull(username)){
            throw new UsernameNotFoundException("用户密码错误");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(USERNAME,
                PASSWORD,
                true,
                true,
                true,
                true,
                authorities);
    }
}
