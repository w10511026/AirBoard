package com.airboard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.airboard")
@EnableJpaRepositories("com.airboard")
@EntityScan("com.airboard")
public class AirbdWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdWebApplication.class, args);
    }

}
