package com.yupi.hewoj.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.hewoj.model.enums.ExecuteCodeStatusEnum;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.judge.codesandbox.CodeSandboxFactory;
import com.yupi.hewoj.judge.codesandbox.CodeSandboxProxy;
import com.yupi.hewoj.judge.codesandbox.Codesandbox;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.hewoj.judge.strategy.JudgeContext;
import com.yupi.hewoj.model.dto.question.JudgeCase;
import com.yupi.hewoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.QuestionSubmit;
import com.yupi.hewoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.hewoj.service.QuestionService;
import com.yupi.hewoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;


    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private JudgeManager judgeManager;




    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR, "提交信息不存在");
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR, "题目不存在");
        }
        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR, "题目正在判题中");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 4）调用沙箱，获取到执行结果
        //使用静态工厂模式创建远程代码沙箱
        Codesandbox codesandbox = CodeSandboxFactory.newInstance(type);

        //使用静态代理来代理codesandbox对象，为codesandbox代码沙箱添加一些功能
        codesandbox = new CodeSandboxProxy(codesandbox);
        String language=questionSubmit.getLanguage();
        String code=questionSubmit.getCode();
        //获取输入样例
        String judgeCaseStr= question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);

        List<String> inputList=judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        //创建沙箱请求对象
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        //沙箱执行
        ExecuteCodeResponse executeCodeResponse = codesandbox.executeCode(executeCodeRequest);



        JudgeInfo judgeInfo=new JudgeInfo();
        //请求头在代码沙箱中未通过校验
        if(executeCodeResponse.getStatus()==ExecuteCodeStatusEnum.NO_AUTH.getValue()){
            judgeInfo.setMessage("无权限");
        }
        //如果编译失败
        else if(executeCodeResponse.getStatus()== ExecuteCodeStatusEnum.COMPILE_FAILED.getValue()){
            judgeInfo.setMessage("编译失败");
        }
        //发生运行时错误
        else if(executeCodeResponse.getStatus()== ExecuteCodeStatusEnum.RUN_FAILED.getValue()){
            judgeInfo.setMessage("运行失败");
            judgeInfo.setMemory(executeCodeResponse.getJudgeInfo().getMemory());
            judgeInfo.setTime(executeCodeResponse.getJudgeInfo().getTime());
        }
        //所有样例运行正常，判断样例输出和正确输出一不一样，判断是否超时
        else{
            //沙箱执行后返回输出
            List<String> outputList = executeCodeResponse.getOutputList();

            // 5）根据沙箱的执行结果，设置题目的判题状态和信息
            JudgeContext judgeContext = new JudgeContext();
            //题目的判题信息
            judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
            //设置所有输入样例
            judgeContext.setInputList(inputList);
            //沙箱返回的输出
            judgeContext.setOutputList(outputList);
            //题目的输入输出样例
            judgeContext.setJudgeCaseList(judgeCaseList);
            //题目
            judgeContext.setQuestion(question);
            //题目提交记录
            judgeContext.setQuestionSubmit(questionSubmit);
            //开始判题
            judgeInfo = judgeManager.doJudge(judgeContext);
        }

        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
