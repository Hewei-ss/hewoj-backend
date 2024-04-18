package com.yupi.hewoj.judge.codesandbox.impl;

import com.yupi.hewoj.judge.codesandbox.Codesandbox;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.hewoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.hewoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.hewoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;


/**
 * 示例代码沙箱（仅为了跑通流程）
 */
public class ExampleCodeSandbox implements Codesandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
