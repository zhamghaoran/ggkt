package com.zhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 20179
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}