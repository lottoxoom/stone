package com.island.stone.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: redission配置
 * @author: LOTTO
 * @create: 2022-01-28 16:58
 **/
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if(StringUtils.isNotBlank(password)){
            config.useSingleServer().setPassword(password);
        }
        return Redisson.create(config);
    }

}
