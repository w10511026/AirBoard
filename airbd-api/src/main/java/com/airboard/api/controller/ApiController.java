package com.airboard.api.controller;

import com.airboard.core.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api("API接口")
@RestController
@RequestMapping("/hello")
public class ApiController {

    @ApiOperation(value = "hello", notes = "呵呵")
    @ApiImplicitParam(name = "msg", value = "消息", required = true, dataType = "String")
    @GetMapping("/list")
    public BaseResult list(String msg) {
        BaseResult result = new BaseResult("查询列表成功！");
        try {
            result.setData(msg);
        } catch (Exception e) {
            log.error("查询列表出错！", e.getMessage());
        }
        return result;
    }

}
