package com.yupi.hewoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.yupi.hewoj.model.dto.question.JudgeConfig;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class SelfAnswerVO implements Serializable {
    /**
     * id
     */
    private Long id;


    /**
     * 查看人数
     */
    private Integer lookCnt;

    /**
     * 点赞人数
     */
    private Integer likeCnt;

    /**
     * 评论数
     */
    private Integer commentCnt;
    /**
     * 题解标题
     */

    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 对象转包装类
     *
     * @param questionAnswer
     * @return
     */
    public static SelfAnswerVO objToVo(QuestionAnswer questionAnswer) {
        if (questionAnswer == null) {
            return null;
        }
        SelfAnswerVO selfAnswerVO = new SelfAnswerVO();
        BeanUtils.copyProperties(questionAnswer, selfAnswerVO);
       return selfAnswerVO;
    }


    private static final long serialVersionUID = 1L;
}
