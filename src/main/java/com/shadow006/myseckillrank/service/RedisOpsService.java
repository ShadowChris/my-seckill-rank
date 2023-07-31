package com.shadow006.myseckillrank.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author caizimo
 * @date 2023/4/12 15:55
 */
@Service
public class RedisOpsService {
    @Resource
    private RedisTemplate redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
