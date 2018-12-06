package com.airboard.airbd.elasticsearch.controller;

import com.airboard.airbd.elasticsearch.dao.UserRepository;
import com.airboard.airbd.elasticsearch.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: Wansghuo
 * @Date: 2018/11/27 16:26
 */
@RestController
@RequestMapping("es")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping("add")
    public String add() {
        User user = new User();
        user.setId(1L);
        user.setUserName("zhangsan");
        user.setPassWord("123456");
        user.setRemark("im a pretty boy!");
        User save = userRepository.save(user);
        return save.toString();
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        User user = userRepository.getUserById(1L);
        userRepository.delete(user);
        return "success";
    }

    /**
     * 局部更新
     *
     * @return
     */
    @RequestMapping("update")
    public String update() {
        User user = userRepository.getUserById(1L);
        user.setUserName("lisi");
        userRepository.save(user);
        System.err.println("update a obj");
        return "success";
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("query")
    public User query() {
        User user = userRepository.getUserById(1L);
        System.err.println(new Gson().toJson(user));
        return user;
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("getall")
    public Iterable<User> getall() {
        Iterable<User> all = userRepository.findAll();
        return all;
    }

}
