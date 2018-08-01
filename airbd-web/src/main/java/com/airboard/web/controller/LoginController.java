package com.airboard.web.controller;

import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.model.vo.UserVO;
import com.airboard.core.service.UserMapperService;
import com.airboard.core.service.UserRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private UserRepositoryService userRepositoryService;

    @GetMapping("")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    public BaseResult loginUser(UserVO userVO) {
        if (StringUtils.isEmpty(userVO.getUserName())) {
            return new BaseResult("用户名不能为空！");
        }
        if (StringUtils.isEmpty(userVO.getPassWord())) {
            return new BaseResult("用户名不能为空！");
        }
        return new BaseResult(true);
    }
}
