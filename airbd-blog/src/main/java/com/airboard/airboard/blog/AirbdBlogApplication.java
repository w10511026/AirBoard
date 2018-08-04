package com.airboard.airboard.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AirbdBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdBlogApplication.class, args);
    }
}
