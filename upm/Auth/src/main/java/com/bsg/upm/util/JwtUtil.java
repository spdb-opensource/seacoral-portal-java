package com.bsg.upm.util;

import com.bsg.upm.domain.JwtUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


@Component
public class JwtUtil implements Serializable {

    private static String SECRET = "tzc-931215";
//    private static Key KEY = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String generateToken(Map<String, Object> claims) {
        //默认签发有效期12小时的token
        Date expirationDate = new Date(System.currentTimeMillis() + 43200000);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes());
        return jwtBuilder.compact();
    }

    public JwtUser getJwtUserFromToken(String token) {
        final ObjectMapper mapper = new ObjectMapper();
        Claims body = parseJwt(token).getBody();
        JwtUser jwtUser = mapper.convertValue(body, JwtUser.class);

        return jwtUser;
    }

    public Jws<Claims> parseJwt(String token) {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token);
        return claimsJws;
    }


    public JwtUser getJwtUserFromTokenInCache(HttpServletRequest request) {
        String jwtToken = new String();
        for (Cookie cookie : request.getCookies()) {
            if ("JwtToken".equals(cookie.getName())) {
                jwtToken = cookie.getValue();
                break;
            }
        }
        return getJwtUserFromToken(jwtToken);
    }
}
