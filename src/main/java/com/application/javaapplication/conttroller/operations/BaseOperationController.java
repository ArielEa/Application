package com.application.javaapplication.conttroller.operations;

import java.util.HashMap;
import java.util.Map;

public class BaseOperationController {

    public Map<String, String> controllerDownMessage(String Code, String Message, String Flag)
    {
        return new HashMap<String, String>() {
            {
                put("code", Code);
                put("flag", Flag);
                put("message", Message);
            }
        };
    }
}
