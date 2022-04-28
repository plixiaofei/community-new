package com.plixiaofei.community.helper;

import com.plixiaofei.community.domain.model.Comment;
import com.plixiaofei.community.domain.vo.CommentVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/4/24 by plixiaofei
 */
public class CommentHelper {
    public static List<CommentVO> LComment2VO(List<Comment> questions) {
        List<CommentVO> res = new ArrayList<>();
        for (Comment question: questions) {
            CommentVO questionVO = new CommentVO();
            BeanUtils.copyProperties(question, questionVO);
            res.add(questionVO);
        }
        return res;
    }
}
