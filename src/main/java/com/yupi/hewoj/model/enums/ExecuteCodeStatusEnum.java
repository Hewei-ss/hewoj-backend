package com.yupi.hewoj.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ExecuteCodeStatusEnum {
    RUN_PROHIBIT("包含违禁词，禁止执行",5),
    NO_AUTH("无权限", 4),
    RUN_FAILED("运行失败", 3),
    COMPILE_FAILED("编译失败", 2),
    SUCCESS("成功", 1);

    private final String msg;

    private final Integer value;

    ExecuteCodeStatusEnum(String msg, Integer value) {
        this.msg = msg;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ExecuteCodeStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ExecuteCodeStatusEnum anEnum : ExecuteCodeStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
