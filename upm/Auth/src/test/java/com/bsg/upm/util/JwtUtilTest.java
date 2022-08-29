package com.bsg.upm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsg.upm.domain.JwtUser;
import com.bsg.upm.domain.RoleDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createJwt() {


    }

    @Test
    public void parseJwt() throws Exception {



    }

    @Test
    public void getJwtUserFromToken() throws Exception {
//        final ObjectMapper mapper = new ObjectMapper();
//        Claims body = jwtUtil.parseJwt("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjp7ImlkIjoiMSIsIm5hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJzZXF1ZW5jZSI6bnVsbCwiZGVzY3JpcHRpb24iOm51bGwsImdtdENyZWF0ZSI6bnVsbCwiY3JlYXRvciI6bnVsbCwiZ210TW9kaWZpZWQiOm51bGwsImVkaXRvciI6bnVsbH0sIm5hbWUiOiLllJDlrZDmiJAiLCJncm91cHMiOltdLCJleHAiOjE1ODE5MTExMjksInVzZXJuYW1lIjoidGFuZ3pjIn0.IQ4VEspuwLgv6rzyXf37Vo7Dvzm76TU6okncd9MAOw2y5XK9qtjSzujSyWhykqP5o7DdSiL_TXNuClKA934CTA")
//                .getBody();
//        JwtUser jwtUser = mapper.convertValue(body, JwtUser.class);
//        System.out.println(jwtUser.toString());
    }
}