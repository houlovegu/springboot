package com.skyworth.oss.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.skyworth.okhttp.util.OkHttpUtils;
import com.skyworth.web.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OssController
 * @Description 文件存储controller
 * @Author sky
 * @Date 2023/5/10 16:02
 * @Version 1.0
 **/
@Api(tags = "OSS模块")
@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource(name = "okHttp")
    private OkHttpClient okHttpClient;

//    @Resource
//    AmazonS3 amazonS3;
//
//    @ApiOperation("测试")
//    @GetMapping("/test")
//    public String getFile() {
//        Bucket awsJavaSdkBucket = amazonS3.createBucket("just-test-create-bucket");
//        return awsJavaSdkBucket.getName();
//    }

    @ApiOperation("測試OkHttp")
    @GetMapping("/http")
    public R test() {
        String s = OkHttpUtils.get(okHttpClient, "http://www.baidu.com", null);
        return R.success(s);
    }

}
