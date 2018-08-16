package com.airboard.asyn.service;

import com.airboard.asyn.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "exchange")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(User sysUserVO) {
        System.out.println("Receiver2  : " + sysUserVO);
    }

}
