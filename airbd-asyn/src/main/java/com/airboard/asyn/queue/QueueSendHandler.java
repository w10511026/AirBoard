package com.airboard.asyn.queue;

import com.airboard.asyn.config.RabbitQueueConfig;
import com.airboard.client.dto.system.SysUserDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSendHandler<T> {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(T t) {
        this.rabbitTemplate.convertAndSend(RabbitQueueConfig.QUEUE_NAME, t);
    }

}
