package com.airboard.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AirbdEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdEurekaApplication.class, args);
    }
}
