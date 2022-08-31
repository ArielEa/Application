package com.application.javaapplication.conttroller;


import com.application.javaapplication.tools.checkauth.IpCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/check")
@Controller
public class IpCheckController
{
    public IpCheck ipCheck;

    @RequestMapping("/ips")
    @ResponseBody
    public String ipsChecked(HttpServletRequest request)
    {
        String ip = request.getRemoteAddr();

        return ip;

//        try {
//            ipCheck.IpCheckProcess("123");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return "ips";
    }
}
