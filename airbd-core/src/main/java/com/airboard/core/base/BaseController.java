package com.airboard.core.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    public static final String USER = "user";

    @Autowired
    protected HttpServletRequest request;

}
