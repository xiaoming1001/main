package com.study.shiwu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        //添加head参数
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> par = new ArrayList<Parameter>();
        //第一个显示的为key 第二个为值   /类型，头
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").build();
        par.add(tokenPar.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.shiwu.controller"))  //限定接口范围，否则展示所有
                .paths(PathSelectors.any()).build().globalOperationParameters(par);   //每个接口调用都填写token
        return docket;
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小明嘟嘟嘟")
                .version("100.0.1")
                .build();
    }
}
