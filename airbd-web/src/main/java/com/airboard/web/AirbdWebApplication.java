package com.airboard.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.airboard.core.dao")
public class AirbdWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdWebApplication.class, args);
    }
}
