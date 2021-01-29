package com.edu118.common.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

/*
 *
 * @Date 2021-01-28 10:41
 * @Author huangshaowu
 *
 */
@Configuration
@Log4j2
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate
            (LettuceConnectionFactory connectionFactory)throws UnknownHostException {
       log.info("自定义RedisTemplate");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //设置序列化方式
        GenericJackson2JsonRedisSerializer redisSerializer  = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer  = new StringRedisSerializer();
        template.setDefaultSerializer(redisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    //告诉 SpringBoot，以自定义的 CacheManager 作为默认的缓存管理器
    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        System.out.println("自定义 RedisCacheManager");
        //创建无锁的RedisCacheWriter
        //RedisCacheWriter 提供了对 Redis 的 set、setnx、get 等命令的访问权限
        //可以由多个缓存实现共享,并负责写/读来自 Redis 的数据
        RedisCacheWriter redisCacheWriter =
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //获取 key 的序列化方式，以字符串的方式转换就可以了
        RedisSerializationContext.SerializationPair<String> serializeKeys =
                RedisSerializationContext.SerializationPair.fromSerializer(new
                        StringRedisSerializer());

        //获取 value 的序列化方式
        GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> serializeValuesWith =
                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer);
        //缓存配置对象 RedisCacheConfiguration
        RedisCacheConfiguration redisCacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(30))
                        //.prefixKeysWith("")
                        .computePrefixWith(cacheName -> cacheName + ":")
                        .disableCachingNullValues() //如果是空值，不缓存
                        .serializeKeysWith(serializeKeys) //设置 key 序列化器
                        .serializeValuesWith(serializeValuesWith); //设置 value 序列化器
        //创建 RedisCacheManager 对象
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(redisCacheWriter)
                .cacheDefaults(redisCacheConfiguration)
                .build();
        return cacheManager;
    }

}
