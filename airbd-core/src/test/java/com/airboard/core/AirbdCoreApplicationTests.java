package com.airboard.core;

import com.airboard.core.dao.system.SysUserMapper;
import com.airboard.core.model.system.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirbdCoreApplicationTests {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void contextLoads() {
        List<SysUser> all = sysUserMapper.findAll();
        System.out.println(all);
    }

}

