package com.application.javaapplication.conttroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/request")
public class ApiRequest {

    @RequestMapping("/entry")
    @ResponseBody
    @CrossOrigin
    public String entry() {
        return "123";
    }

    public String stockOut() {
        return "stockOut";
    }

    public String refund()
    {
        return "refund";
    }

    public String delivery()
    {
        return "delivery";
    }
}
