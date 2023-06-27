package com.application.javaapplication.conttroller.WmsReceiveTaskController;

import com.application.javaapplication.operation.verify.CommonVerifyOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WmsReceiveTaskController
{
//    @Autowired
//    public CommonVerifyOperation verifyOperation;

    @RequestMapping("/router/services")
    @ResponseBody
    public static String WmsReceive(HttpServletRequest request, CommonVerifyOperation verifyOperation)
    {
        // Some messages from third partner's request .
        String appKey = request.getParameter("appKey");
        String format = request.getParameter("format");
        String v = request.getParameter("v");
        String signMethod = request.getParameter("sign_method");
        String method = request.getParameter("method");
        String timestamp = request.getParameter("timestamp");
        String customerId = request.getParameter("customerId");
        String partnerId = request.getParameter("partner_id");

        Map<String, String> Parameters = new HashMap<String, String>() {
            {
                put("appKey", appKey);
                put("format", format);
                put("v", v);
                put("sign_method", signMethod);
                put("method", method);
                put("timestamp", timestamp);
                put("customerId", customerId);
                put("partner_id", partnerId);
            }
        };
        // without response. for a Boolean message .
        Boolean checkMessage = verifyOperation.checkAuth(Parameters);

        System.out.println(checkMessage);

        System.out.println(Parameters);

        return "It's test demo!!!";
    }
}
