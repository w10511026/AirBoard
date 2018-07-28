package com.airboard.web.controller;

import com.airboard.core.base.JedisTemplate;
import com.airboard.core.dto.Users;
import com.airboard.core.service.UserService;
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
    private UserService userService;
    @Autowired
    private JedisTemplate jedisTemplate;

    @GetMapping("/redis")
    public String redis() {
        jedisTemplate.set("ws", "晚上", 0);
        Object ws = jedisTemplate.get("ws");
        return ws.toString();
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<Users> all = userService.findAll();

        //Users one = userService.getOne(2L);
        return all.toString();
    }
}
