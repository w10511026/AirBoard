package com.airboard.web.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.base.JedisTemplate;
import com.airboard.core.model.Users;
import com.airboard.core.service.UserMapperService;
import com.airboard.core.service.UserRepositoryService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController extends BaseController {

    @Autowired
    UserRepositoryService userRepositoryService;
    @Autowired
    private UserMapperService userMapperService;
    @Autowired
    private JedisTemplate jedisTemplate;

    @GetMapping("/redis")
    public String redis() {
        jedisTemplate.set("ws", "晚上", 0);
        Object ws = jedisTemplate.get("ws");
        return ws.toString();
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/getAll")
    public BaseResult getAll() {
        BaseResult baseResult = new BaseResult();
        try {
            List<Users> all = userRepositoryService.listAll();
            Users users = new Users();
            users.setUserName("laowang");
            users.setPassWord("wangshuo");
            userRepositoryService.save(users);
            Users one = userMapperService.getById(2L);
            baseResult.setData(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResult;
    }
}
