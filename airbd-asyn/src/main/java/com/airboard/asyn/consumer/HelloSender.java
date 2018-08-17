package com.airboard.asyn.consumer;

import com.airboard.asyn.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User sysUserVO) {
        sysUserVO = new User();
        sysUserVO.setUserName("hehe");
        this.rabbitTemplate.convertAndSend("hello", sysUserVO);
    }

}
