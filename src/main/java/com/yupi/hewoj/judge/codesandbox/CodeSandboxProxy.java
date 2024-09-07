package com.yupi.hewoj.judge.codesandbox;

import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


/**
 * 代码沙箱静态代理类(相当于增强代码沙箱的能力)
 */
@Slf4j
public class CodeSandboxProxy implements Codesandbox {

    private Codesandbox codesandbox;


    public CodeSandboxProxy(Codesandbox codesandbox){
        this.codesandbox=codesandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codesandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}