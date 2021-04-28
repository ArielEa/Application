package com.application.javaapplication.conttroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class DemoController
{
    @GetMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request)
    {
        String inputKey = request.getParameter("abd");

        return "Index Page : out put : " + inputKey;


    }

    @ResponseBody
    @RequestMapping("/post_test")
    public String postTest(HttpServletRequest request)
    {
        String postKey = request.getParameter("post");

        return "Post key : " + postKey;
    }
}
