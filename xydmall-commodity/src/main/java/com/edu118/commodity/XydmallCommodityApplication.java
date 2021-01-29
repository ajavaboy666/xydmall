package com.edu118.commodity;

import com.edu118.common.aop.RedisCacheAspect;
import com.edu118.common.config.RedisConfig;
import com.edu118.common.exception.XydmallExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
//@Import({XydmallExceptionHandler.class, RedisConfig.class, RedisCacheAspect.class})
@Import({XydmallExceptionHandler.class, RedisConfig.class})
@EnableCaching
public class XydmallCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydmallCommodityApplication.class, args);
    }

}
