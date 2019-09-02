package com.herokuapp.newtechserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class Newtechserver2Application {

    public static void main(String[] args) {
        SpringApplication.run(Newtechserver2Application.class, args);
    }

    @Configuration
    public class WebConfiguration extends WebMvcConfigurationSupport {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/uploads/");
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}




