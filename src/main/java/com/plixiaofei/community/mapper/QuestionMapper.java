package com.plixiaofei.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plixiaofei.community.domain.model.Question;
import org.apache.ibatis.annotations.Mapper;

/**
* @author plixiaofei
* @description 针对表【question(帖子表格)】的数据库操作Mapper
* @createDate 2022-04-14 15:29:59
* @Entity com.plixiaofei.common.domain.model.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




