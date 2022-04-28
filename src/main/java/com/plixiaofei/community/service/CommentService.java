package com.plixiaofei.community.service;

import com.plixiaofei.community.domain.dto.CommentDTO;
import com.plixiaofei.community.domain.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plixiaofei.community.domain.vo.CommentVO;

import java.util.List;

/**
* @author plixiaofei
* @description 针对表【comment(用户评论表)】的数据库操作Service
* @createDate 2022-04-24 10:04:17
*/
public interface CommentService extends IService<Comment> {

    CommentVO saveComment(String fromUsername, CommentDTO commentDTO);

    List<CommentVO> getCommentsByQuestionId(Long questionId);

    List<CommentVO> getCommentsByUsername(String username, int curPage);
}
