package com.plixiaofei.community.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.plixiaofei.community.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created on 2022/4/10 by plixiaofei
 */
@Component
public class JwtUtil {

    private static String jwtKey;
    private static Algorithm algorithm;

    @Value("${jwtKey}")
    public void setJwtKeyAndAlgorithm(String jwtKey) {
        JwtUtil.jwtKey = jwtKey;
        JwtUtil.algorithm = Algorithm.HMAC256(JwtUtil.jwtKey);
    }

    public static String getToken(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        return JWT.create()
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(Calendar.getInstance().getTime())
                .withClaim("username", username)
                .sign(algorithm);
    }

    public static String getToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        return JWT.create()
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(Calendar.getInstance().getTime())
                .withClaim("username", user.getUsername())
                .sign(JwtUtil.algorithm);
    }

    public static boolean verifyToken(String token) {
        return JWT.require(JwtUtil.algorithm).build().verify(token) != null;
    }
}
