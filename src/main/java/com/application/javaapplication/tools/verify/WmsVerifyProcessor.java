package com.application.javaapplication.tools.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WmsVerifyProcessor {

    @Autowired
    public ApplicationContext applicationContext;

    public Boolean WmsVerifyProcessor()
    {

        return true;
    }
}
