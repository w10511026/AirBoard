package com.airboard.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api("API接口")
@RestController
@RequestMapping("/hello")
public class ApiController {

    @ApiOperation(value = "hello", notes = "呵呵")
    @ApiImplicitParam(name = "msg", value = "消息", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/list")
    public String list(String msg) {
        return msg;
    }

}
