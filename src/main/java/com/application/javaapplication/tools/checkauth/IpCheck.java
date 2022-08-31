package com.application.javaapplication.tools.checkauth;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IpCheck {

    private String [] limitedIps = new String[]{
            "43.132.193.41",
            "116.231.62.105"
    };

    private Integer maxCount = 100;

    private String redisKey = "warehouse_limit_keys";

    private Integer expireTime = 60;

    private void setRedis()
    {

    }

    public Boolean IpCheckProcess(String receivedIp) throws Exception
    {

        return true;
    }
}
