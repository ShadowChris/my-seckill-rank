package com.shadow006.myseckillrank.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author caizimo
 * @date 2023/4/12 15:52
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Spring Data Redis 提供了多种序列化器供我们选择。以下是一些常见的序列化器及其含义和使用场景：
     * 1. StringRedisSerializer：字符串序列化器，将数据序列化为字符串。它适用于简单的场景，如键和值都是字符串的情况。这个序列化器效率较高，但不适合复杂对象的序列化。
     * 2. GenericToStringSerializer：通用字符串序列化器，可以将任何对象序列化为字符串。它适用于简单对象的序列化，但可能不适合复杂对象，因为它会将对象转换为字符串，可能导致信息丢失。
     * 3. Jackson2JsonRedisSerializer：基于 Jackson 库的 JSON 序列化器，可以将对象序列化为 JSON 字符串。适用于复杂对象的序列化和反序列化。使用这个序列化器时，需要注意添加 Jackson 库的依赖。
     * 4. GenericJackson2JsonRedisSerializer：通用的基于 Jackson 库的 JSON 序列化器，类似于Jackson2JsonRedisSerializer，但它可以处理更多的类型，不需要为每个类型创建单独的Jackson2JsonRedisSerializer。这个序列化器适用于序列化和反序列化各种复杂对象。
     * 5. JdkSerializationRedisSerializer：基于 Java 序列化的序列化器，使用 Java 标准序列化机制将对象序列化为字节数组。适用于复杂对象的序列化和反序列化，但效率较低，而且序列化后的数据较大。此外，使用这个序列化器时，需要确保对象实现了Serializable接口。
     * 6. OxmSerializer：基于 O/X Mapping 的序列化器，使用 XML 来序列化和反序列化对象。适用于需要将对象序列化为 XML 的场景。使用这个序列化器时，需要添加 O/X Mapping 库的依赖。
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置Key的序列化方式 (字符串和hashmap)
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // 设置Value的序列化方式 (字符串和hashmap)
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
