package com.skyworth.web.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),
    VALIDATE_FAILED(1001, "参数校验失败"),
    ERROR(500, "失败");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
