package com.edu118.commodity;

import com.edu118.common.config.RedisConfig;
import com.edu118.common.exception.MallExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
//@Import({mallExceptionHandler.class, RedisConfig.class, RedisCacheAspect.class})
@Import({MallExceptionHandler.class, RedisConfig.class})
@EnableCaching
public class MallCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCommodityApplication.class, args);
    }

}
