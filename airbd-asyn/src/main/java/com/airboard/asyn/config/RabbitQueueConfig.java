package com.airboard.asyn.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ 队列配置
 * @Author <a href="mailto:wangshuo@ksudi.com">wangshuo</a>
 * @Date 2018/8/17 17:20
 */
@Configuration
public class RabbitQueueConfig {

    public final static String QUEUE_NAME = "I_QUEUE";

    @Bean
    public Queue Queue() {
        return new Queue(QUEUE_NAME);
    }

}
