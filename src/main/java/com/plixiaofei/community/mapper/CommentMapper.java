package com.plixiaofei.community.mapper;

import com.plixiaofei.community.domain.model.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author plixiaofei
* @description 针对表【comment(用户评论表)】的数据库操作Mapper
* @createDate 2022-04-24 10:04:17
* @Entity com.plixiaofei.community.domain.model.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




