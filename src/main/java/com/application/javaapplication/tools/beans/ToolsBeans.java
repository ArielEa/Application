package com.application.javaapplication.tools.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsBeans
{
    @Bean
    public String TestService()
    {
        return "test service";
    }
}
