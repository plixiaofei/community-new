package com.plixiaofei.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plixiaofei.community.domain.model.Comment;
import com.plixiaofei.community.service.CommentService;
import com.plixiaofei.community.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author plixiaofei
* @description 针对表【comment(用户评论表)】的数据库操作Service实现
* @createDate 2022-04-22 20:27:23
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




