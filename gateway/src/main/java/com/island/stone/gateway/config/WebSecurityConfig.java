package com.island.stone.gateway.config;

import com.island.stone.gateway.service.PeopleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: 登录鉴权
 * @author: LOTTO
 * @create: 2022-01-29 17:48
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)//全部方法验证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PeopleDetailService peopleDetailService;

    /**
     * 配置内存登录用户验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("").password("").roles("")
//                .and()
//                .withUser("").password("").roles("");

        auth.parentAuthenticationManager(authenticationManagerBean()).userDetailsService(peopleDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /** 路径拦截，表明路径访问所对应的权限，角色，认证信息*/
                .authorizeRequests()
                .antMatchers("/resources/**","/user","").permitAll()
                .antMatchers("/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                /** 表单认证*/
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .failureForwardUrl("/login?error")
                .loginPage("/login").permitAll()
                .and()
                /** 注销相关的配置*/
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()
                .and()
                /** 配置basic登录*/
                .httpBasic()
                .disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
