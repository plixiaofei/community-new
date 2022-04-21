package com.plixiaofei.community.controller;

import com.plixiaofei.community.domain.dto.PublishQuestionDTO;
import com.plixiaofei.community.domain.model.Question;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.domain.vo.QuestionVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2022/4/14 by plixiaofei
 */
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/api/listQuestion", method = RequestMethod.GET)
    private Result<Object> listQuestion(@RequestParam("curPage") Integer curPage) {
        List<QuestionVO> questions = questionService.listQuestion(curPage);
        return new Result<>(ResultCode.SUCCESS, questions);
    }

    @RequestMapping(value = "/api/publishQuestion", method = RequestMethod.POST)
    public Result<Object> publishQuestion(@RequestBody PublishQuestionDTO questionDTO) {
        questionService.saveQuestion(questionDTO);
        return new Result<>(ResultCode.SUCCESS, questionDTO);
    }

    @RequestMapping(value = "/api/getDetailedQuestion", method = RequestMethod.GET)
    public Result<Object> getDetailedQuestion(@RequestParam("questionId") String questionId) {
        Question question = questionService.getById(questionId);
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        return new Result<>(ResultCode.SUCCESS, questionVO);
    }

    @RequestMapping(value = "/api/listUserQuestions", method = RequestMethod.GET)
    public Result<Object> listUserQuestions(@RequestParam("username") String username) {
        List<QuestionVO> data = questionService.getQuestionsByUsername(username);
        return new Result<>(ResultCode.SUCCESS, data);
    }

    @RequestMapping(value = "/api/searchQuestion", method = RequestMethod.GET)
    public Result<Object> searchQuestion(@RequestParam("word") String word) {
        List<QuestionVO> data = questionService.searchQuestion(word);
        return new Result<>(ResultCode.SUCCESS, data);
    }
}
