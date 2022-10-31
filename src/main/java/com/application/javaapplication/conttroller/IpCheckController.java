package com.application.javaapplication.conttroller;


import com.application.javaapplication.tools.checkauth.IpCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/check")
@Controller
public class IpCheckController
{
    public IpCheck ipCheck;

    public String LocalIp = "0:0:0:0:0:0:0:1";

    @Value(value = "${spring.profiles.active}")
    private String EnvironmentMode;


    @RequestMapping("/ips")
    @ResponseBody
    public String ipsChecked(HttpServletRequest request)
    {
        String ip = request.getRemoteUser();

        return ip;
    }
}
