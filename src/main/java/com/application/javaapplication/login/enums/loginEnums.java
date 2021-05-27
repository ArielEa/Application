package com.application.javaapplication.login.enums;

public enum loginEnums {
    LOGIN_EMPTY_ERROR(1001, "Empty user"),
    LOGIN_INVALID_ERROR(1002, "Invalid user"),
    LOGIN_EMPTY_PASSWORD(1003, "Empty Password"),
    LOGIN_ERROR_PASSWORD(1004, "Error Password"),
    ADMIN_DATA_NUMS_ERROR(1005, "ERROR DATA[customers' nums]"),
    LOGIN_UPDATE_FAILURE(1006, "UPDATE LOGIN INFO FAILURE"),
    VERIFY_SECRET_INVALID(1007, "Invalid AccountCode SECRET");

    private Integer Code;

    private String Explain;

    loginEnums(int Code, String Explain)
    {
        this.Code = Code;
        this.Explain = Explain;
    }

    public int getCode()
    {
        return this.Code;
    }

    public String getExplain()
    {
        return this.Explain;
    }
}
