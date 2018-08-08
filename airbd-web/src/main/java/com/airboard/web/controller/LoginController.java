package com.airboard.web.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @PostMapping("/login")
    public BaseResult login(HttpServletRequest request, SysUserVO userBO) {
        BaseResult result = new BaseResult(true, "登录成功！");
        if (StringUtils.isEmpty(userBO.getUserName())) {
            return new BaseResult("用户名不能为空！");
        }
        if (StringUtils.isEmpty(userBO.getPassWord())) {
            return new BaseResult("用户名不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userBO.getUserName(), userBO.getPassWord());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            token.clear();
            return new BaseResult("用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            token.clear();
            return new BaseResult("密码错误！");
        } catch (AuthenticationException e) {
            token.clear();
            return new BaseResult("用户或密码错误！");
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

}
