package com.poc.producer.config;

import com.poc.producer.sse.dto.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.poc.producer")
public class ProducerConfig {

    @Bean(name = "nullMessage")
    public Message nullMessage(){
        return new Message("null message", -1);
    }

    @Bean(name = "errorMessage")
    public Message errorMessage(){
        return new Message("error message", -1);
    }

}
