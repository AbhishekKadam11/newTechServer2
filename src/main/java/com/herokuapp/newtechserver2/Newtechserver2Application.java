package com.herokuapp.newtechserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Newtechserver2Application {

    public static void main(String[] args) {
        SpringApplication.run(Newtechserver2Application.class, args);


    }

    @Configuration
    @EnableWebMvc
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/","classpath:/other-resources/");
        }
    }

}




