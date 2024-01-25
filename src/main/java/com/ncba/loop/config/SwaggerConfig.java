package com.ncba.loop.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;



public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .displayName("Loop NCBA Test")
                .group("loop-public")
                .pathsToMatch("/public/**")
                .build();
    }

}
