package com.application.javaapplication.entity.enums;

import com.application.javaapplication.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;

public enum UserEnums
{
    ID("id", "Integer", 1),
    OWNER_CODE("owner_code", "String",1),
    ADMIN_NAME("admin_name", "String",1),
    PASSWORD("password", "String", 1),
    USER_IDENTITY("user_identity", "Integer",1),
    USER_STATUS("user_status", "Integer",1),
    REGISTER_TIME("register_time", "Datetime",1),
    UPDATE_TIME("update_time", "Datetime",1),
    LOGIN_TIME("login_time", "Datetime",1),
    LOGIN_OUT("login_out", "Datetime",1),
    TOKEN("token", "String",1);

    private String Value;
    private String Mode;
    private Integer Effective;

    UserEnums(String Value, String Mode, Integer Effective) {
        this.Value = Value;
        this.Mode = Mode;
        this.Effective = Effective;
    }

    public String getValue()
    {
        return this.Value;
    }

    public String getMode()
    {
        return this.Mode;
    }

    public Integer getEffective()
    {
        return this.Effective;
    }
}
