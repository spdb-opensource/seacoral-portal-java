package com.bsg.upm.interceptor;

import com.bsg.upm.domain.JwtUser;
import com.bsg.upm.util.JwtUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        JwtUser jwtUser = null;

        try {
            String jwtToken = request.getHeader("X-Token");
            String channel = request.getHeader("channel");
            if(null!=channel && !"".equals(channel)) {
            	return true;
            }
            
            jwtUser = jwtUtil.getJwtUserFromToken(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jwtUser == null) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            out.write("未授权登录");
            out.close();

            return false;
        }
        return true;
    }


    protected void setErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }


}
