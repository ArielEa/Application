package com.application.javaapplication.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class testRequest {

    @RequestMapping("/A")
    @ResponseBody
    @CrossOrigin
    public String testA(HttpServletRequest request)
    {
        String cookie = request.getHeader("Cookie");
        String token = request.getHeader("token");
        String headers = request.getHeader("headers");

        return "token 是 " + token + "\n cookie 是" + cookie + "\n" + headers;
    }
}
