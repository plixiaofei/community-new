package com.plixiaofei.community.controller;

import com.auth0.jwt.interfaces.Claim;
import com.plixiaofei.community.domain.dto.CommentDTO;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created on 2022/4/22 by plixiaofei
 */
@RestController
public class CommentController {
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    // TODO 完成评论功能
    public Result<Object> comment(HttpServletRequest request,
                                  @RequestBody CommentDTO commentDTO) {
        Map<String, Claim> claims = JwtUtil.getTokenClaims(request.getHeader("Authorization").replace("\"", ""));
        String username = claims.get("username").asString();
        System.out.println(username);
        System.out.println(commentDTO);
        return null;
    }
}
