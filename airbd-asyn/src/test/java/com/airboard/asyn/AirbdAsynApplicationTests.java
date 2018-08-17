package com.airboard.asyn;

import com.airboard.asyn.consumer.HelloSender;
import com.airboard.asyn.consumer.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirbdAsynApplicationTests {

    @Autowired
    TopicSender topicSender;
    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        topicSender.send1();
    }

}
