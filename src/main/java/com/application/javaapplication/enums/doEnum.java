package com.application.javaapplication.enums;

import java.util.HashMap;
import java.util.Map;

public enum doEnum implements BaseEnum
{
    SYSTEM(1, "super"),
    ROBOT(2, "robot");

    private int code;
    private String displayName;

    private doEnum(int code, String displayName)
    {
        this.code = code;
        this.displayName = displayName;
    }


    public int getCode()
    {
        return code;
    }

    public String getDisplayName()
    {
        return displayName;
    }
}
