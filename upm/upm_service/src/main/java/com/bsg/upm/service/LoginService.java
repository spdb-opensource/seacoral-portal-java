package com.bsg.upm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bsg.upm.util.DecodeBase64Util;
import com.bsg.upm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.LoginCheck;
import com.bsg.upm.check.resultenum.LoginChkRsEnum;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.form.LoginForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 登录登出逻辑处理类
 *
 * @author HCK
 */
@Service
public class LoginService extends BaseService {

    @Autowired
    private LoginCheck loginCheck;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @param request   HttpServletRequest
     * @param loginForm LoginForm 登录表单数据
     * @return Result
     */
    public Result login(HttpServletRequest request, LoginForm loginForm) {
        CheckResult checkResult = loginCheck.checkSave(loginForm);
        if (checkResult.getCode() != LoginChkRsEnum.SUCCESS.getCode()) {
            logger.error(loginfo(checkResult));
            return Result.failure(checkResult);
        }

        String password = DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes());
//        String password = DigestUtils.md5DigestAsHex(DecodeBase64Util.decode(loginForm.getPassword()));
        UserDO userDO = userDAO.get(loginForm.getUsername(), password);
//        HttpSession session = request.getSession();
//        session.setAttribute("user", userDO);

        //创建Jwt Token返回给HTTP请求的头信息Authorization字段
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("user_id", userDO.getUsername());
        claims.put("name", userDO.getName());
        //目前的表结构为单用户单角色
//        ArrayList<String> roles = new ArrayList<>();
//        roles.add("admin");
//        claims.put("roles", roles);
        claims.put("role", userDO.getRole());
        claims.put("introduction", "Im super man");
        claims.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        String token = jwtUtil.generateToken(claims);

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", token);

        return Result.success(data);
    }

    /**
     * 登出
     *
     * @param request HttpServletRequest
     */
    public Result logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Result.success();
    }

}
