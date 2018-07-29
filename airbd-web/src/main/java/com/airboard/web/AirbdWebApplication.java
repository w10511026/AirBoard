package com.airboard.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.airboard")
@MapperScan("com.airboard.core.dao")
@EnableJpaRepositories("com.airboard")
@EntityScan("com.airboard")
public class AirbdWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdWebApplication.class, args);
    }
}
