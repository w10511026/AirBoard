package com.airboard.web.controller;

import com.airboard.core.dao.UserMapper;
import com.airboard.core.dao.UserRepository;
import com.airboard.core.dto.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description </br>
 * @Version Ver 1.0
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/6/26
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/getAll")
    public String getAll() {
        List<Users> all = userMapper.findAll();
        return all.toString();
    }
}
