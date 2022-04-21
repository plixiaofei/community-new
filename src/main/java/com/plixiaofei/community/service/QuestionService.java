package com.plixiaofei.community.service;

import com.plixiaofei.community.domain.dto.PublishQuestionDTO;
import com.plixiaofei.community.domain.model.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plixiaofei.community.domain.vo.QuestionVO;

import java.util.List;

/**
* @author plixiaofei
* @description 针对表【question(帖子表格)】的数据库操作Service
* @createDate 2022-04-14 15:29:59
*/
public interface QuestionService extends IService<Question> {

    void saveQuestion(PublishQuestionDTO question);

    List<QuestionVO> listQuestion(int curPage);

    List<QuestionVO> getQuestionsByUsername(String username);

    List<QuestionVO> searchQuestion(String word);
}
