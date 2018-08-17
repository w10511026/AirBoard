package com.airboard.web.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.util.JWTUtil;
import com.airboard.core.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        List<SysUserVO> userResult = sysUserService.getByUserName(userBO.getUserName());
        if (CollectionUtils.isEmpty(userResult)) {
            return new BaseResult("用户名不存在！");
        }
        SysUserVO sysUserVO = userResult.get(0);
        if (!sysUserVO.getPassWord().equals(userBO.getPassWord())) {
            return new BaseResult("密码错误！");
        }
        result.setData(JWTUtil.createToken(sysUserVO.getUserName(), sysUserVO.getPassWord()));
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
