package com.edu118.externalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XydmallExternalserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydmallExternalserviceApplication.class, args);
    }

}
