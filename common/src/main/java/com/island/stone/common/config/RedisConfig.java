package com.island.stone.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @description: redis缓存配置
 * @author: LOTTO
 * @create: 2022-01-25 22:19
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory factory){
                Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
                ObjectMapper om = new ObjectMapper();
                om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
                om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
                jackson2JsonRedisSerializer.setObjectMapper(om);

                RedisSerializer<String> redisSerializer = new StringRedisSerializer();
                //配置序列化，key前缀
                RedisCacheConfiguration config =RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                        .computePrefixWith(key -> String.format("%s%s", key, ":"));
                return RedisCacheManager.builder(factory).cacheDefaults(config).build();
        }

        /**
         * 注册redisTemplate，作为消息队列的发布者
         */
        @Bean
        public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
                RedisMessageListenerContainer container = new RedisMessageListenerContainer();
                container.setConnectionFactory(connectionFactory);
                return container;
        }

}
