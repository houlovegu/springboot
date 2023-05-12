package com.skyworth.web.response;

import com.skyworth.web.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName R
 * @Description 统一响应类
 * @Author sky
 * @Date 2023/5/11 11:28
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@ApiModel
public class R<T> implements Serializable {

    /**
     * 响应码
     **/
    @ApiModelProperty(value = "响应信息", required = true)
    private Integer code;

    /**
     * 响应消息
     **/
    @ApiModelProperty(value = "响应信息")
    private String msg;

    /**
     * 响应数据
     **/
    @ApiModelProperty(value = "响应的数据")
    private T data;


    private R(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private R(String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.data = null;
        this.msg = msg;
    }

    private R(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功时候的调用
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data) {
        return new R<>(data);
    }

    /**
     * 根据返回的状态对象， 构建返回结果
     * @param resultEnum
     * @param <T>
     * @return
     */
    public static <T> R<T> build(ResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> build(Integer code, String msg) {
        return new R<T>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> build(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    /**
     * 失败的调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> R<T> error(String codeMsg) {
        return new R<>(codeMsg);
    }

    /**
     * 失败的调用 将返回结果传入
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> error(T data) {
        return new R<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
    }
}
