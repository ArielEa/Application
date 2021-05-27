package com.application.javaapplication.conttroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/api")
public class ApiController
{
    @GetMapping("/")
    @ResponseBody // 输入模版
    @CrossOrigin // 支持跨域
    public String apiIndex()
    {
        String row = "api";
        return row;
    }

    @GetMapping("/test_api")
    @ResponseBody
    @CrossOrigin // 支持跨域
    public Map getApiTest(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("id");

        System.out.println(id);

        String test = "api test";
        Map list = new HashMap<>();
        list.put(1, "testA");
        list.put(2, "testB");
        list.put(3, "testC");
        return list;
    }

    @GetMapping("/test_array_api")
    @ResponseBody
    @CrossOrigin
    public ArrayList getArrayList()
    {
        ArrayList list = new ArrayList<>();
        list.add(0, "listA");
        list.add(1, "listB");
        list.add(2, "listC");
        return list;
    }

    @PostMapping("/post_test")
    @ResponseBody
    @CrossOrigin
    public String getPostTest(HttpServletRequest request, @RequestBody LinkedHashMap<String, String> params)
    {
        /**
         *  Postman Post RAW[json]
         */
        String str = "" ;
        String strN = "" ;

        str = request.getParameter("api");

        strN = params.get("api");

        return "PostA :" + str + "\n" + "PostB :" + strN;
    }

}
