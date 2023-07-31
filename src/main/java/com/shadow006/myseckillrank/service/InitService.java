package com.shadow006.myseckillrank.service;

import com.shadow006.myseckillrank.configs.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author caizimo
 * @date 2023/4/6 16:47
 */
@Service
@Log4j
public class InitService {
    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 1天有24小时
     */
    final static int DAY2HOUR = 24;
    /**
     * 一个月有30天
     */
    final static int MONTH2DAY = 30;

    /**
     * 初始化一个月的历史数据
     */
    public void init30day() {
        // 计算当前小时的key
        long currentHour = System.currentTimeMillis() / (1000 * 60 * 60);
        // 从当前时间，以小时为单位，往前推一个月
        for (int i = 1; i < DAY2HOUR * MONTH2DAY; i++) {
            String key = Constants.HOUR_KEY + (currentHour - i);
            this.initMember(key);
            System.out.println(key);
        }

    }
    public void initMember(String key) {
        Random rand = new Random();
        for (int i = 0; i < 26; i++) {
            ZSetOperations zSetOperations = this.redisTemplate.opsForZSet();
            zSetOperations.add(key, String.valueOf((char)('a' + i)), rand.nextInt(10));
        }
    }
}
