package com.shadow006.myseckillrank.service;

import com.shadow006.myseckillrank.configs.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author caizimo
 * @date 2023/4/14 11:14
 */
@Service
@Slf4j
public class TaskService {
    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        log.info("启动初始化 ...");
//        // 2. 定时5秒钟，模拟微博的热度刷新 (例如模拟点赞 收藏 评论的热度值更新)
//        new Thread(() -> this.refreshDataHour()).start();
//        // 3，定时1小时合并统计 天、周、月的排行榜。
//        new Thread(() -> this.refreshData()).start();
    }
    public void refreshHour() {
        // 计算当前小时key
        long hour = getCurrentHourKey();

        // 为26个字母排行，为每个字母生成随机数作为score
        Random rand = new Random();
        for (int i = 0; i < 26; i++) {
            // 新增：ZINCRBY, (key, value, score)，即：set名，set里的value名，新增的数值
            this.redisTemplate.opsForZSet().incrementScore(
                    Constants.HOUR_KEY + hour,
                    String.valueOf((char)('a' + i)), rand.nextInt(10)
            );
        }
    }

    /**
     * 刷新当天统计数据（如果在天的中间，则用前23h数据当作当前数据）
     */
    public void refreshDay() {
        long hour = getCurrentHourKey();
        List<String> pastHourKey = new ArrayList<>();
        // 计算近24小时的key (0:00-23:59，包括当前23小时，需要往前推22h)
        for (int i = 1; i < 23; i++) {
            String key = Constants.HOUR_KEY + (hour - i);
            pastHourKey.add(key);
        }

        // 合并一天数据
        this.redisTemplate.opsForZSet().unionAndStore(Constants.HOUR_KEY + hour, pastHourKey, Constants.DAY_KEY);

        // 设置小时key40天过期
        for (int i = 0; i < 24; i++) {
            String key = Constants.HOUR_KEY + (hour - 1);
            this.redisTemplate.expire(key, 40, TimeUnit.DAYS);
        }
    }

    // 获取当前小时的key
    public long getCurrentHourKey() {
        return System.currentTimeMillis() / (1000 * 60 * 60);
    }

}
