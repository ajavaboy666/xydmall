package com.edu118.commodity.config;

import com.edu118.common.config.RedisUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *
 * @Date 2021-01-28 16:53
 * @Author huangshaowu
 *
 */
@Configuration
public class MallConfig {
    @Bean
    public RedisUtils xydRedisUtils(){
        return new RedisUtils();
    }
}
