package com.application.javaapplication.conttroller;

import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.enums.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class CatalogListController {

    public Boolean CatalogListAction()
    {
        return true;
    }
}
