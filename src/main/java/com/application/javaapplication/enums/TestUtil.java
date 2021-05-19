package com.application.javaapplication.enums;

import org.springframework.stereotype.Component;

@Component
public class TestUtil
{
    public String send(String value)
    {
        return "value is : " + value;
    }
}
