package com.application.javaapplication.conttroller;

import com.application.javaapplication.enums.*;
import com.application.javaapplication.redis.RedisUtil;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
//@RequestMapping("/api")
public class DemoController
{
    @Autowired
    private CommonEnum commonEnum;

    @Autowired
    private EnumsUtil enumsUtil;

    @GetMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request)
    {
        String value = CallEnum.STRING_TEST_A.getDisplayName();
//        String value = "";

        String inputKey = request.getParameter("abd");

        return "Index Page : out put : " + inputKey + "value = " + value;
    }

    @ResponseBody
    @RequestMapping("/post_test")
    public String postTest(HttpServletRequest request) {
        String postKey = request.getParameter("post");

        return "Post key 123 : " + postKey;
    }

    @RequestMapping("/redis_test")
    @ResponseBody
    public String redisStr(HttpServletRequest request)
    {
        commonEnum.setEnumMap();

        String a = enumsUtil.enumsMap().getTest();

        String message = "";

//        message = redisUtil.get("bu");

        return "redis Message : " + message + "a" + a;
    }
}
