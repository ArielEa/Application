package com.application.javaapplication.conttroller;

import com.application.javaapplication.tools.dosql.SqlDoctrineExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController extends VerifyController
{
    @Autowired
    private SqlDoctrineExtends EM;

    public <k, v> Map<k, v> output( Map<k, v> mapList )
    {

        return (Map<k, v>) mapList;
    }
}
