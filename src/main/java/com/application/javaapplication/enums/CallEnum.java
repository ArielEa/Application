package com.application.javaapplication.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;

@Getter
@JsonFormat
public enum CallEnum implements BaseEnum
{
    STRING_TEST_A(1, "A"),
    STRING_TEST_B(2, "B"),
    STRING_TEST_C(3, "C");

    private Integer code;
    private String displayName;

    CallEnum(Integer code, String displayName)
    {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }
}
