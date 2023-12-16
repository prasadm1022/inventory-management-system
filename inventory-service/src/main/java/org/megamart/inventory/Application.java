package org.megamart.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
    {
        return application.sources( Application.class );
    }

    @Bean
    public RestTemplate restTemplate( RestTemplateBuilder restTemplateBuilder )
    {
        return restTemplateBuilder.build();
    }

    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }
}
