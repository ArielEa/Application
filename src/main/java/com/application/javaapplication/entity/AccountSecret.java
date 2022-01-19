package com.application.javaapplication.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@lombok.Data
@TableName("account_secret")
@Getter
@Setter
public class AccountSecret
{
    @TableField(value = "id")
    public Integer id;

    @TableField(value = "account_code")
    public String accountCode;

    @TableField(value = "account_secret")
    public String accountSecret;

    @TableField(value = "daily_status")
    public Integer dailyStatus;
}
