package com.airboard.core;

import com.airboard.core.dao.UserMapper;
import com.airboard.core.dto.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirbdCoreApplicationTests {

    //@Autowired
    //UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<Users> all = userMapper.findAll();
        System.out.println(all);
    }

}
