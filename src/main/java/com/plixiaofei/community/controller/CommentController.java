package com.plixiaofei.community.controller;

import com.auth0.jwt.interfaces.Claim;
import com.plixiaofei.community.domain.dto.CommentDTO;
import com.plixiaofei.community.domain.model.Comment;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.domain.vo.CommentVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.service.CommentService;
import com.plixiaofei.community.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022/4/22 by plixiaofei
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    // TODO 完成评论功能
    public Result<Object> comment(HttpServletRequest request,
                                  @RequestBody CommentDTO commentDTO) {
        Map<String, Claim> claims = JwtUtil.getTokenClaims(request.getHeader("Authorization").replace("\"", ""));
        String fromUsername = claims.get("username").asString();
        CommentVO commentVO = commentService.saveComment(fromUsername, commentDTO);
        return new Result<>(ResultCode.SUCCESS, commentVO);
    }

    @RequestMapping(value = "/api/getCommentsByQuestionId", method = RequestMethod.GET)
    public Result<Object> getCommentsByQuestionId(@RequestParam("questionId") Long questionId) {
        List<CommentVO> commentVOs = commentService.getCommentsByQuestionId(questionId);
        return new Result<>(ResultCode.SUCCESS, commentVOs);
    }

    @RequestMapping(value = "/api/getCommentsByUsername", method = RequestMethod.GET)
    public Result<Object> getCommentsByUsername(@RequestParam("curPage") Integer curPage,
                                                HttpServletRequest request) {
        Map<String, Claim> claims = JwtUtil.getTokenClaims(request.getHeader("Authorization").replace("\"", ""));
        String username = claims.get("username").asString();
        List<CommentVO> commentVOs = commentService.getCommentsByUsername(username, curPage);
        return new Result<>(ResultCode.SUCCESS, commentVOs);
    }

}
