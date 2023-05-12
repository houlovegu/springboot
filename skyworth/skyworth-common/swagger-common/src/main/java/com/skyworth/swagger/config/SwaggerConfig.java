package com.skyworth.swagger.config;

import com.github.xiaoymin.knife4j.core.extend.OpenApiExtendSetting;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description Swagger在线文档配置
 * @Author sky
 * @Date 2023/5/11 9:28
 * @Version 1.0
 **/
@Import({OpenApiExtensionResolver.class, OpenApiExtendSetting.class})
@EnableSwagger2WebMvc
@Configuration
public class SwaggerConfig {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket docket() {
        // 添加接口请求头参数配置 没有的话 可以忽略
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token")
                .description("令牌")
                .defaultValue("")
                .modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否启动swagger 默认启动
                .enable(true)
                //所在分组
                .groupName("skyworth")
                .select()
                //指定扫描的包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .apis(RequestHandlerSelectors.basePackage("com.skyworth.*.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("skyworth"))
                .globalOperationParameters(pars);
    }


    /**
     * 添加摘要信息
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("skyworth-system-api")//标题
                .description("skyworth-system-api-v1.0.0")//描述
                .contact(new Contact("", "", ""))
                .version("v1.0.0")
                .build();
    }

}
