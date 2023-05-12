package com.skyworth.web.exception;

import com.skyworth.web.enums.ResultEnum;
import com.skyworth.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常捕获
 * @Author sky
 * @Date 2023/5/11 11:23
 * @Version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认的全局异常处理,其他异常再添加
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e) {
        log.error("发生全局异常，exception = {}", e.getMessage(), e);
        return R.error(e.getMessage());
    }

    /**
     * 处理自定义异常类，业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public R handleCustomException(CustomException e) {
        log.error("发生自定义异常，exception = {}", e.getMessage(), e);
        return R.build(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R noRequestHandlerFoundExceptionHandler(MissingServletRequestParameterException e) {
        return R.build(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    public R handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return R.build(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


//重写了ErrorController自定义了/error的返回格式，此处不在捕获
//    @ExceptionHandler({NoHandlerFoundException.class})
//    @ResponseBody
//    public R handleNoHandlerFoundException(Exception e) {
//        return R.build(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase());
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("参数校验异常！原因是:", e);
        return getErrorMessage(e.getBindingResult().getAllErrors());
    }

    /**
     * （注意）result 是自己定义的全局接口返回格式
     *
     * @param allErrors
     * @return
     */
    private R getErrorMessage(final List<ObjectError> allErrors) {
        final StringBuffer message = new StringBuffer();
        int index = 0;
        for (final ObjectError error : allErrors) {
            if (index == 0) {
                message.append(error.getDefaultMessage());
            } else {
                message.append(";").append(error.getDefaultMessage());
            }
            index++;

        }

        String messages = String.valueOf(message);
        log.error(messages);
        return R.build(ResultEnum.VALIDATE_FAILED.getCode(), messages);

    }
}
