package com.poc.producer.config;

import com.poc.producer.sse.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.poc.producer")
public class AppConfig implements WebMvcConfigurer {


    @Bean(name = "nullMessage")
    public Message nullMessage(){
        return new Message("null message", -1);
    }

    @Bean(name = "errorMessage")
    public Message errorMessage(){
        return new Message("error message", -1);
    }

}
