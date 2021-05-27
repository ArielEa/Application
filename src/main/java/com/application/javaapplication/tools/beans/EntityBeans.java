package com.application.javaapplication.tools.beans;

import com.application.javaapplication.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityBeans {
    @Bean
    public User user()
    {
        return new User();
    }

//    @Bean
//    public Account
}
