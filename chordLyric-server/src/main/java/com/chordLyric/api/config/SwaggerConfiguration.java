package com.chordLyric.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.chordLyric.api.models.references.ApiInfoReference;
import com.chordLyric.api.utils.ChordLyricProfiles;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("!" + ChordLyricProfiles.PROD)
public class SwaggerConfiguration {
	
	@Autowired
	private ApiInfoReference apiInfoReference;
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chordLyric.api.controllers"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(this.chordLyricApiInfo());
    }


    private ApiInfo chordLyricApiInfo() {
        return new ApiInfoBuilder()
                .title(this.apiInfoReference.getInfo("title"))
                .description(this.apiInfoReference.getInfo("description"))
                .version(this.apiInfoReference.getInfo("apiVersion"))
                .build();
    }
}