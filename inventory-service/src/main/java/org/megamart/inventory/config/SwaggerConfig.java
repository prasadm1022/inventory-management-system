package org.megamart.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.Collections;

/**
 * Swagger Configurations
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket( DocumentationType.SWAGGER_2 )
                       .directModelSubstitute( LocalTime.class, String.class ).select()
                       .apis( RequestHandlerSelectors.basePackage( "org.megamart.inventory.controller" ) )
                       .apis( RequestHandlerSelectors.any() )
                       .paths( PathSelectors.any() )
                       .build()
                       .apiInfo( apiInfo() );
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Inventory Service API",
                "Inventory Service",
                "v1",
                "Terms of service",
                new Contact( "Prasad Madusanka", "https://www.linkedin.com/in/prasad-madusanka-73ab1b147", "prasadm1022@gmail.com" ),
                "License of API", "API license URL", Collections.emptyList() );
    }
}