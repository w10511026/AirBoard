package com.airboard.airboard.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("post")
public class PostController {

    private final static String BASE_DIR = "/";

    @GetMapping("")
    public String index(HttpServletRequest request, Model model) {
        return BASE_DIR + "post";
    }

}
