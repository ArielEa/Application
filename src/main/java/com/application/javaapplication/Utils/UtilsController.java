package com.application.javaapplication.Utils;

import com.application.javaapplication.conttroller.VerifyController;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.*;

@Component
public class UtilsController
{
    static String errorFlag;

    static String successFlag;

    static int defaultErrorCode;

    static int defaultSuccessCode;

    {
        errorFlag = "failure";
        successFlag = "success";
        defaultErrorCode = 500;
        defaultSuccessCode = 200;
    }

    @ResponseBody
    public <k, v> Map<k ,v> doErrorHandle(String Message, int Code)
    {
        Map<Object, Object> fatherList = new HashMap<>();

        Map<Object, Object> childList = new HashMap<>();

        childList.put("SubCode", 1001);
        childList.put("SubMessage", Message);
        childList.put("SubPath", "error utils");

        fatherList.put("flag", errorFlag);
        fatherList.put("code", Code);
        fatherList.put("SubInfo", childList);

        return (Map<k, v>) fatherList;
    }

    public <k,v> Map<k, v> doSuccessHandle(String Message, int Code)
    {
        return new HashMap<>();
    }
}
