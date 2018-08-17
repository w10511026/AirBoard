package com.airboard.asyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.airboard"})
@EnableDiscoveryClient
@ComponentScan("com.airboard")
@SpringBootApplication
public class AirbdAsynApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbdAsynApplication.class, args);
    }
}
