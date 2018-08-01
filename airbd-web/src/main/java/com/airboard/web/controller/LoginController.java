package com.airboard.web.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.bo.SysUserBO;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/1
 */
@Slf4j
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private SysUserRepositoryService sysUserRepositoryService;

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
    public BaseResult login(HttpServletRequest request, SysUserBO userBO) {
        BaseResult result = new BaseResult(true, "登录成功！");
        if (StringUtils.isEmpty(userBO.getUserName())) {
            return new BaseResult("用户名不能为空！");
        }
        if (StringUtils.isEmpty(userBO.getPassWord())) {
            return new BaseResult("用户名不能为空！");
        }
        List<SysUser> list = sysUserRepositoryService.getByUserName(userBO.getUserName());
        if (CollectionUtils.isEmpty(list)) {
            return new BaseResult("用户名不存在！");
        }
        List<SysUser> users = sysUserRepositoryService.getByUserNameAndPassWord(userBO.getUserName(), userBO.getPassWord());
        if (CollectionUtils.isEmpty(users)) {
            return new BaseResult("用户或密码错误！");
        }
        HttpSession session = request.getSession();
        session.setAttribute(USER, userBO);
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }
}
