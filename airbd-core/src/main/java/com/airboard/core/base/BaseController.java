package com.airboard.core.base;

import com.airboard.core.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 通用contoller
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
public abstract class BaseController {

    public static final String USER = "user";

    @Autowired
    protected HttpServletRequest request;

    public Users getUserFromSession() {
        HttpSession session = this.request.getSession();
        Users users = (Users) session.getAttribute(USER);
        if (null == users) {
            return null;
        }
        return users;
    }
}
