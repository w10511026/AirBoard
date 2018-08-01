package com.airboard.core.base;

import com.airboard.core.bo.SysUserBO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    public static final String USER = "user";

    @Autowired
    protected HttpServletRequest request;

    public SysUserBO getUserFromSession() {
        HttpSession session = this.request.getSession();
        SysUserBO users = (SysUserBO) session.getAttribute(USER);
        if (null == users) {
            return null;
        }
        return users;
    }
}
