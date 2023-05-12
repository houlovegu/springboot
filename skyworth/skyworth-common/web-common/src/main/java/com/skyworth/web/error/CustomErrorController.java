package com.skyworth.web.error;


import com.skyworth.web.response.R;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName CustomErrorController
 * @Description 自定义/404   /500返回格式
 * @Author sky
 * @Date 2023/5/11 13:56
 * @Version 1.0
 **/
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public R error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return R.build(status.value(), status.getReasonPhrase());
    }
    /** * 获取错误编码 * @param request * @return */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
