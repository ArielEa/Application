package com.application.javaapplication.tools.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class toolServiceModel
{
    @Value("${SYSTEM_KEY}")
    public String SystemKey;

    @Value("${SYSTEM_VERSION}")
    public float SystemVersion;

    @Value("${SYSTEM_SECRET}")
    public String SystemSecret;


    public String getSystemKey()
    {
        return SystemKey;
    }

    public float getSystemVersion()
    {
        return SystemVersion;
    }

    public String getSystemSecret()
    {
        return SystemSecret;
    }
}
