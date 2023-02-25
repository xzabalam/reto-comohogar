package com.como.hogar.config.docs;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    private static final String GRUPO = "controladores";
    private static final String[] PATHS = {
            "/api/**"
    };

    @Bean
    public GroupedOpenApi getDocket() {
        return GroupedOpenApi
                .builder()
                .packagesToScan("com.como.hogar.web.controllers")
                .group(GRUPO)
                .pathsToMatch(PATHS)
                .build();
    }
}
