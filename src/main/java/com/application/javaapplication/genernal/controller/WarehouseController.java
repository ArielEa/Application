package com.application.javaapplication.genernal.controller;

import com.application.javaapplication.conttroller.VerifyController;
import com.application.javaapplication.login.utils.LoginJdbcUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController extends VerifyController
{
    @GetMapping("/list")
    @ResponseBody
    @CrossOrigin
    public <k,v> Map<k, v> warehouseListAction( HttpServletRequest request, HttpServletResponse response)
    {
//        @Validated(VerifyController.class) VerifyController verifyController, BindingResult result,
        Map<Integer, String> warehouseList = new HashMap<>();

        String headerToken = request.getHeader("project_token");

        warehouseList.put(1, "abc");

        // todo:: 切片化注解
//        this.init();

        warehouseList.put(1, "headerToken");

        return (Map<k, v>) warehouseList;
    }
}
