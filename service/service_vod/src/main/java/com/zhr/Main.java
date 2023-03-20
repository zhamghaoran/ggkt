package com.zhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 20179
 */

@SpringBootApplication
@MapperScan
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}