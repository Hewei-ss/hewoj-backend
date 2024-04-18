package com.yupi.hewoj.judge.codesandbox;

import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface Codesandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
