package com.application.javaapplication.genernal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrdersController
{
    @GetMapping("/history")
    @CrossOrigin
    @ResponseBody
    public Map<Integer, String> history()
    {
        Map<Integer, String> list = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
            }
        };
        return list;
    }
}
