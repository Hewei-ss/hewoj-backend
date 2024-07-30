package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.AnswerFavour;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 31695
* @description 针对表【answer_favour(题解收藏)】的数据库操作Mapper
* @createDate 2024-06-06 16:51:45
* @Entity generator.domain.AnswerFavour
*/
public interface AnswerFavourMapper extends BaseMapper<AnswerFavour> {

    List<AnswerFavour> getthumbanswerlistPage(@Param("userId") long userId);
}




