package com.plixiaofei.community.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plixiaofei.community.domain.dto.PublishQuestionDTO;
import com.plixiaofei.community.domain.dto.QuestionESDTO;
import com.plixiaofei.community.domain.model.Question;
import com.plixiaofei.community.domain.model.User;
import com.plixiaofei.community.domain.vo.QuestionVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.helper.QuestionHelper;
import com.plixiaofei.community.mapper.QuestionMapper;
import com.plixiaofei.community.service.QuestionService;
import com.plixiaofei.community.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author plixiaofei
* @description 针对表【question(帖子表格)】的数据库操作Service实现
* @createDate 2022-04-14 15:29:59
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{
    @Value("${page.question}")
    private Integer QUESTION_EACH_PAGE;

    @Autowired
    private ElasticsearchClient esClient;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void saveQuestion(String username, PublishQuestionDTO questionDTO) {
        List<String> usernames = userService.list().stream().map(User::getUsername).collect(Collectors.toList());
        if (!usernames.contains(username)) {
            throw new CustomException(ResultCode.USER_NOT_EXISTS);
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        Date currentTime = new Date(System.currentTimeMillis());
        question.setUsername(username);
        question.setCreateTime(currentTime);
        question.setUpdateTime(currentTime);
        question.setIsDeleted(0);
        int insertFlag = questionMapper.insert(question);
        if (insertFlag == 0) {
            throw new CustomException(ResultCode.PUB_QUESTION_FAIL);
        } else {
            QuestionESDTO questionESDTO = new QuestionESDTO();
            BeanUtils.copyProperties(question, questionESDTO);
            try {
                esClient.index(i -> i.index("question")
                        .id(questionESDTO.getId() + "")
                        .document(questionESDTO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<QuestionVO> listQuestion(int curPage) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<Question> page = questionMapper.selectPage(new Page<>(curPage, QUESTION_EACH_PAGE), queryWrapper);
        List<QuestionVO> questionVOS = new ArrayList<>();
        for (Question question: page.getRecords()) {
            QuestionVO temp = new QuestionVO();
            BeanUtils.copyProperties(question, temp);
            questionVOS.add(temp);
        }
        return questionVOS;
    }

    @Override
    public List<QuestionVO> getQuestionsByUsername(String username) {
        List<QuestionVO> questionVOS = new ArrayList<>();

        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Question> questionQueryWrapper = queryWrapper.eq("username", username)
                .orderByDesc("create_time");
        Page<Question> page = questionMapper.selectPage(new Page<>(1, 5), questionQueryWrapper);
        // 转 VO
        for (Question question: page.getRecords()) {
            QuestionVO temp = new QuestionVO();
            BeanUtils.copyProperties(question, temp);
            questionVOS.add(temp);
        }
        return questionVOS;
    }

    @Override
    public List<QuestionVO> searchQuestion(String word) {
        List<QuestionVO> questionVOS = null;
        SearchResponse<QuestionESDTO> result = null;
        try {
            result = esClient.search(s -> s.index("question")
                            .query(q -> q.multiMatch(f -> f.fields("title", "shortDescription").query(word))),
                    QuestionESDTO.class);
            List<Hit<QuestionESDTO>> hits = result.hits().hits();
            if (result.hits().total().value() == 0) {
                throw new CustomException(ResultCode.QUESTION_UNSEARCHABLE);
            }
            List<Long> ids = hits
                    .stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(QuestionESDTO::getId)
                    .collect(Collectors.toList());
            List<Question> questions = questionMapper.selectBatchIds(ids);
            questionVOS = QuestionHelper.LQuestion2VO(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionVOS;
    }
}




