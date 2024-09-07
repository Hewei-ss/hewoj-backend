package com.yupi.hewoj.judge;

import com.yupi.hewoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.hewoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yupi.hewoj.judge.strategy.JudgeContext;
import com.yupi.hewoj.judge.strategy.JudgeStrategy;
import com.yupi.hewoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.hewoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}