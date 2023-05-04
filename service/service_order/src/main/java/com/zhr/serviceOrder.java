package com.zhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jjking
 */
@SpringBootApplication
@EnableDiscoveryClient
public class serviceOrder {
    public static void main(String[] args) {
        SpringApplication.run(serviceOrder.class, args);
    }
}