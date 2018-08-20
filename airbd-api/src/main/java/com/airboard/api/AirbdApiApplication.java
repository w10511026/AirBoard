package com.airboard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.airboard")
@EnableJpaRepositories("com.airboard")
@EntityScan("com.airboard")
public class AirbdApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdApiApplication.class, args);
    }

}
