package com.airboard.core.util;

import com.airboard.core.exception.ValidateException;
import com.airboard.core.exception.ValidateExceptionEnum;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染工具类
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonObject));
        } catch (IOException e) {
            throw new ValidateException(ValidateExceptionEnum.WRITE_ERROR);
        }
    }
}
