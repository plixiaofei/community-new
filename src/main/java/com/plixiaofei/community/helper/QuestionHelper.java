package com.plixiaofei.community.helper;

import com.plixiaofei.community.domain.model.Question;
import com.plixiaofei.community.domain.vo.QuestionVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/4/20 by plixiaofei
 */
public class QuestionHelper {
    public static List<QuestionVO> LQuestion2VO(List<Question> questions) {
        List<QuestionVO> res = new ArrayList<>();
        for (Question question: questions) {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            res.add(questionVO);
        }
        return res;
    }
}
