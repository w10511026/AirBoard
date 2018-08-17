package com.airboard.api.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.api.service.system.SysUserService;
import com.airboard.core.util.JWTUtil;
import com.airboard.client.dto.system.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "登录服务")
@RestController
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public BaseResult<SysUserDTO> login(@RequestBody SysUserDTO userBO) {
        BaseResult result = new BaseResult(true, "登录成功！");
        if (StringUtils.isEmpty(userBO.getUserName())) {
            return new BaseResult("用户名不能为空！");
        }
        if (StringUtils.isEmpty(userBO.getPassWord())) {
            return new BaseResult("用户名不能为空！");
        }
        List<SysUserDTO> userResult = sysUserService.getByUserName(userBO.getUserName());
        if (CollectionUtils.isEmpty(userResult)) {
            return new BaseResult("用户名不存在！");
        }
        SysUserDTO sysUserVO = userResult.get(0);
        if (!sysUserVO.getPassWord().equals(userBO.getPassWord())) {
            return new BaseResult("密码错误！");
        }
        sysUserVO.setToken(JWTUtil.createToken(sysUserVO.getUserName(), sysUserVO.getPassWord()));
        result.setData(sysUserVO);
        return result;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

}
