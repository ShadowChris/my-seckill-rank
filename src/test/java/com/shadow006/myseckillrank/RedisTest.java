package com.shadow006.myseckillrank;
import com.shadow006.myseckillrank.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private TaskService taskService;

    @Test
    public void set() {
//        redisTemplate.opsForValue().set("test:set1", "testValue1");
//        redisTemplate.opsForSet().add("test:set2", "asdf");
//        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
//        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
//        redisTemplate.opsForHash().put("hash1", "name3", "lms3");
//        System.out.println(redisTemplate.opsForValue().get("test:set1"));
//        System.out.println(redisTemplate.opsForHash().get("hash1", "name1"));
    }

    @Test
    public void delete() {
//        System.out.println(redisTemplate.delete("hash1"));
        Set<String> keys = redisTemplate.keys("test*");
        System.out.println(redisTemplate.delete(keys));
    }
    @Test
    public void ZSet() {
        System.out.println(redisTemplate.opsForZSet().incrementScore("test", "value", 5));
        System.out.println(redisTemplate.opsForZSet().incrementScore("test", "value", 10));
    }

    @Test
    public void genData() {
//        taskService.refreshHour();
        taskService.refreshDay();
    }
}

