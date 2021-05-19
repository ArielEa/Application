package com.application.javaapplication.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class testRequest {

    @RequestMapping("/A")
    @ResponseBody
    @CrossOrigin
    public String testA()
    {
        return "testA";
    }
}
