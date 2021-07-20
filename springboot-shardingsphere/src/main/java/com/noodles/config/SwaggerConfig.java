package com.noodles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统应用API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.noodles.web"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("秒杀分库分表")
                .description("一个能够让秒杀真正分布式的案例")
                .termsOfServiceUrl("1111111111111")
                .contact(new Contact("Noodles笔记 ", "1111111111111", "645465550@qq.com"))
                .version("1.0").build();
    }
}
