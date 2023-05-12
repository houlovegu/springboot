package com.skyworth.web.exception;

import lombok.Data;

/**
 * @ClassName CustomException
 * @Description 自定义异常
 * @Author sky
 * @Date 2023/5/11 11:46
 * @Version 1.0
 **/
@Data
public class CustomException extends RuntimeException {

    private Integer code;

    private String msg;

    public CustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
