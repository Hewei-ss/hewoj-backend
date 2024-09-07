package com.yupi.hewoj.judge.strategy;

import com.yupi.hewoj.model.dto.question.JudgeCase;
import com.yupi.hewoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
