package com.edu118.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class mallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(mallOrderApplication.class, args);
    }

}
