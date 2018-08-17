package com.airboard.asyn.queue;

import com.airboard.client.dto.system.SysUserDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = "I_QUEUE")
public class QueueReceiveHandler<T> {

    @RabbitHandler
    public void process(T t) {
        ObjectMapper mapper = new ObjectMapper();
        SysUserDTO sysUserDTO = null;
        try {
            sysUserDTO = mapper.readValue((JsonParser) t, SysUserDTO.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        System.out.println("QUEUE_NAME: I_QUEUE, Receiver Message: " + sysUserDTO.toString());
    }

}
