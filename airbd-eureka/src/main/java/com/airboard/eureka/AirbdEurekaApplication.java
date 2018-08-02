package com.airboard.eureka;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@EnableAdminServer
@Configuration
@EnableEurekaServer
@SpringBootApplication
public class AirbdEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdEurekaApplication.class, args);
    }
}
