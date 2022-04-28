package com.plixiaofei.community.controller;

import com.plixiaofei.community.domain.dto.PublishQuestionDTO;
import com.plixiaofei.community.domain.model.Question;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.domain.vo.QuestionVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.service.QuestionService;
import com.plixiaofei.community.util.JwtUtil;
import com.plixiaofei.community.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @RequestMapping(value = "/api/getQuestionCount", method = RequestMethod.GET)
    public Result<Object> getQuestionCount() {
        int size = questionService.list().size();
        return new Result<>(ResultCode.SUCCESS, size);
    }

    @RequestMapping(value = "/api/publishQuestion", method = RequestMethod.POST)
    public Result<Object> publishQuestion(HttpServletRequest request,
                                          @RequestBody PublishQuestionDTO questionDTO) {
        String authorization = request.getHeader("Authorization").split("\"")[1];
        String username = "";
        if (JwtUtil.verifyToken(authorization)) {
            username = JwtUtil.getTokenClaims(authorization).get("username").asString();
            System.out.println(username);
        }
        questionService.saveQuestion(username, questionDTO);
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

    @Value("${storage.questionImg}")
    private String storePath;
    // 上传文件回传地址
    @RequestMapping(value = "/api/uploadImg", method = RequestMethod.POST)
    public Result<Object> uploadImg(@RequestParam("questionImg") MultipartFile questionImg) {
        System.out.println(questionImg.getSize());
        String randomImgName = MyUtil.randomFileName(questionImg.getOriginalFilename());
        Path dest = Paths.get(storePath, randomImgName);
        try {
            questionImg.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result<>(ResultCode.SUCCESS, dest.toString());
    }
}
