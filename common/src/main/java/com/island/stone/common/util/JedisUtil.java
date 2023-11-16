package com.island.stone.common.util;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @description: jedis工具类
 * @author: LOTTO
 * @create: 2022-01-28 13:25
 **/
public class JedisUtil {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;


    /**
     * 尝试获取锁
     * @param jedis Redis客户端
     * @param lockKey 锁key值
     * @param requestId 请求标识
     * @param expireTime 过期时间
     * @return 是否成功
     */
    public static boolean tryLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return
     */
    public static boolean unlock(Jedis jedis, String lockKey, String requestId){
        // lua脚本删除锁，保证原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return RELEASE_SUCCESS == result;
    }


}
