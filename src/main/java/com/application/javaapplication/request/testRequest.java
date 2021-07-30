package com.application.javaapplication.request;


import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class testRequest
{
    @Autowired
    private Environment environment;

    @RequestMapping("/A")
    @ResponseBody
    @CrossOrigin
    public String testA(HttpServletRequest request)
    {
        String cookie = request.getHeader("Cookie");
        String token = request.getHeader("token");
        String headers = request.getHeader("headers");

        String environment = this.environment.getProperty("spring.profiles.active");

        return "token 是 " + token + "\n cookie 是" + cookie + "\n" + headers+ "环境是 ：" + environment;
    }
}
