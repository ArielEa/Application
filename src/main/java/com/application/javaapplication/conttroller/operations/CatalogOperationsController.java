package com.application.javaapplication.conttroller.operations;

import com.alibaba.fastjson.JSON;
import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.conttroller.RequestParamsController;
import com.application.javaapplication.enums.*;
import com.application.javaapplication.exec.catalog.CatalogCreateExec;
import com.application.javaapplication.tools.checkauth.RequestParams;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CatalogOperationsController extends BaseOperationController
{
    @Autowired
    private RequestParams requestParams;

    @Autowired
    private CatalogCreateExec catalogCreateExec;

    @PostMapping("/generate/catalog")
    @ResponseBody
    public Map<String, String> GenerateCatalogAction(HttpServletRequest request)
    {
        Map<String, String> parameters;

        try {
            // 验证参数收否存在
            requestParams.requestCheckauth(request, "name", "nature");
            // 获取参数列表
            parameters = requestParams.getRequestParams();
            // 准进行数据插入
            catalogCreateExec.GenerateNewProject(parameters);
        }catch (Exception exception) {
            return controllerDownMessage("500", exception.getMessage(), "failure");
        }
        return controllerDownMessage("0", "添加成功", "success");
    }
}
