package com.yupi.hewoj.judge.codesandbox;

import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.hewoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.hewoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodesandboxTest {


    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCodeByProxy() {
        Codesandbox codesandbox = CodeSandboxFactory.newInstance(type);
        codesandbox = new CodeSandboxProxy(codesandbox);
        String code = "import java.io.BufferedReader;\n" +
                "import java.io.File;\n" +
                "import java.io.InputStreamReader;\n" +
                "import java.nio.file.Files;\n" +
                "import java.nio.file.Paths;\n" +
                "import java.util.Arrays;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果:\" + (a + b));\n" +
                "    }\n" +
                "}\n";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codesandbox.executeCode(executeCodeRequest);


    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            Codesandbox codesandbox = CodeSandboxFactory.newInstance(type);
            String code = "int main() { }";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
            ExecuteCodeResponse executeCodeResponse = codesandbox.executeCode(executeCodeRequest);
        }
    }
}