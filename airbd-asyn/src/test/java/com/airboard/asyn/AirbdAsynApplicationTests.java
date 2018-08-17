package com.airboard.asyn;

import com.airboard.asyn.queue.QueueSendHandler;
import com.airboard.client.dto.system.SysUserDTO;
import com.airboard.client.service.web.system.SysUserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirbdAsynApplicationTests {

    @Autowired
    SysUserClient sysUserClient;
    @Autowired
    private QueueSendHandler queueSendHandler;

    @Test
    public void hello() throws Exception {
        List<SysUserDTO> admin = sysUserClient.getByUserName("admin");
        System.out.println(admin.toString());
    }

    @Test
    public void test() throws Exception {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUserName("zhjagnsan");
        queueSendHandler.send(sysUserDTO);
    }

}
