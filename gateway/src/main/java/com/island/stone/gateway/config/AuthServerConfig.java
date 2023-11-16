package com.island.stone.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @description: 权限配置
 * @author: LOTTO
 * @create: 2022-01-29 16:32
 **/
@Configuration
@EnableAuthorizationServer//开启认证授权服务
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String SOURCE_ID = "";
    private static final int ACCESS_TOKEN_TIMER = 60 * 60 * 24;
    private static final int REFRESH_TOKEN_TIMER = 60 * 60 * 24 * 30;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("stone")
//                .resourceIds(SOURCE_ID)
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all").authorities("ADMIN")
                .accessTokenValiditySeconds(ACCESS_TOKEN_TIMER)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }


//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter(){
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter(){
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//                return super.enhance(accessToken, authentication);
//            }
//        };
//
//
//        //测试，资源服务器使用同一字符使用对称加密，生产使用RSA非堆成加密
//        accessTokenConverter.setSigningKey("");
//        return accessTokenConverter;
//    }
//
//    @Bean
//    public TokenStore tokenStore(){
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        return tokenStore;
//    }

}
