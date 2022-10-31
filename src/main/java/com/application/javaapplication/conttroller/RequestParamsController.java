package com.application.javaapplication.conttroller;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;

public class RequestParamsController
{
    private HttpServletRequest request;

    String [] RequestParams;

    public Boolean requestCheckauth(String [] params)
    {
        RequestParams = params;
        this.requestParamsFinder();
        return true;
    }

    public void requestParamsFinder()
    {
        System.out.println(RequestParams);
    }
}
