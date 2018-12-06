package com.airboard.airbd.trading;

import com.airboard.airbd.trading.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirbdTradingApplicationTests {

    @Autowired
    HelloController helloController;

    @Test
    public void contextLoads() {
        helloController.hello();
    }

}
