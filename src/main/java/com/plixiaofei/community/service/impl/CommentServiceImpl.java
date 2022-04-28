package com.plixiaofei.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plixiaofei.community.domain.dto.CommentDTO;
import com.plixiaofei.community.domain.model.Comment;
import com.plixiaofei.community.domain.model.Question;
import com.plixiaofei.community.domain.vo.CommentVO;
import com.plixiaofei.community.domain.vo.UserVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.helper.CommentHelper;
import com.plixiaofei.community.mapper.CommentMapper;
import com.plixiaofei.community.service.CommentService;
import com.plixiaofei.community.service.QuestionService;
import com.plixiaofei.community.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author plixiaofei
* @description 针对表【comment(用户评论表)】的数据库操作Service实现
* @createDate 2022-04-24 10:04:17
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Value("${page.notification}")
    private Long PAGE_EACH_NOTIFICATION;

    @Transactional
    @Override
    public CommentVO saveComment(String fromUsername, CommentDTO commentDTO) {
        List<Long> questionId = questionService.list()
                .stream().map(Question::getId).collect(Collectors.toList());
        if (!(questionId.contains(commentDTO.getQuestionId()))) {
            throw new CustomException(ResultCode.COMMENT_QUESTION_NOT_EXISTS);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setFromUsername(fromUsername);
        comment.setCommentTime(new Date(System.currentTimeMillis()));
        int insertFlag = commentMapper.insert(comment);
        if (insertFlag == 0) {
            throw new CustomException(ResultCode.INSERT_COMMENT_FAIL);
        }
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        UserVO userInfo = userService.getUserInfo(fromUsername);
        commentVO.setUserVO(userInfo);
        return commentVO;
    }

    @Transactional
    @Override
    public List<CommentVO> getCommentsByQuestionId(Long questionId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", questionId)
                .orderByDesc("comment_time");
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVO> commentVOS = CommentHelper.LComment2VO(comments);
        for (CommentVO commentVO: commentVOS) {
            UserVO userInfo = userService.getUserInfo(commentVO.getFromUsername());
            commentVO.setUserVO(userInfo);
        }
        return commentVOS;
    }

    @Override
    public List<CommentVO> getCommentsByUsername(String username, int curPage) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("from_username", username)
                .orderByDesc("comment_time");
        Page<Comment> commentPage = commentMapper.selectPage(new Page<>(curPage, PAGE_EACH_NOTIFICATION), queryWrapper);
        List<CommentVO> commentVOS = CommentHelper.LComment2VO(commentPage.getRecords());
        for (CommentVO commentVO: commentVOS) {
            UserVO userInfo = userService.getUserInfo(commentVO.getFromUsername());
            commentVO.setUserVO(userInfo);
        }
        return commentVOS;
    }
}




