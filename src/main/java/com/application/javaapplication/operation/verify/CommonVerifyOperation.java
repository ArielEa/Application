package com.application.javaapplication.operation.verify;

import com.application.javaapplication.tools.verify.WmsVerifyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonVerifyOperation
{
    @Autowired
    public WmsVerifyProcessor $wmsVerifyProcessor;

    public Boolean checkAuth(Map<String, String> parameters)
    {
        // With a ASCII code sort this messages.

        return true;
    }
}
