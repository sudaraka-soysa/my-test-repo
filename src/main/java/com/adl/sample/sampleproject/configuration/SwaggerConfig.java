package com.adl.sample.sampleproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors;


import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Dinuka_08966 on 9/23/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 .apis(RequestHandlerSelectors.basePackage("com.adl"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo()).pathProvider(new BasePathAwareRelativePathProvider("/CONTEXT_PATH" ));

    }

    private ApiInfo apiInfo() {
        String title ="GROUP_ID_ARTIFACT_ID";
        return new ApiInfo(
                title,
                "Welcome to Huawei CBS MIFE REST API Integration Platform",
                "v1.0",
                "Terms of service",
                new Contact("John Doe", "http://www.axiatadigitallabs.com", "jhon.doe@axiatadigitallabs.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        public BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }

}