package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionAnswerOne;
import org.apache.ibatis.annotations.Param;

/**
* @author 31695
* @description 针对表【question_answer(题目题解表)】的数据库操作Mapper
* @createDate 2024-04-24 18:58:15
* @Entity generator.domain.QuestionAnswer
*/
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswerOne> {


    QuestionAnswer getAnswerByAnswerId(@Param("answerId") long answerId);
}




