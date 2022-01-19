package com.application.javaapplication.tools.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@lombok.Data
public class Result<T> {
    @ApiModelProperty(value="用户名称",required=true)
    private String name;
    @ApiModelProperty(value="用户年龄",required=true)
    private Integer age;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", message" + message + "]";
    }
}
