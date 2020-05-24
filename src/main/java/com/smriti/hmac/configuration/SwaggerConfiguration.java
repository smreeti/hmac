package com.smriti.hmac.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author smriti ON 11/01/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.smriti.hmac"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(metaData())
                .globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                                .name("Authorization")
                                .description("HMAC Authentication Code")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()));
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "HMAC DEMO",
                "This is the RESTful API collections of all resources with HMAC code for default admin:" +
                        " HmacSHA512 admin:1:COGENT:649a8184-03f5-4cad-8dfe-e1fef82ce436:017084416528259:JL9Nf3ftvW8YDy2" +
                        "KFfh6Yff8mo0Ye9SOm5CEZmMpiAcd/R62wa3EaWF6dnHxnfipD5XKJV9W5Y6QdPFAjWpf/Q==",
                "V1",
                "",
                new Contact(
                        "",
                        "",
                        ""),
                "",
                "",
                Collections.emptyList());
    }
}
