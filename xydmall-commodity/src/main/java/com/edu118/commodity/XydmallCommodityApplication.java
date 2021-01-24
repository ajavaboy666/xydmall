package com.edu118.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XydmallCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydmallCommodityApplication.class, args);
    }

}
