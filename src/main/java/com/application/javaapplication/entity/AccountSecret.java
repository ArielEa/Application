package com.application.javaapplication.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@TableName("account_secret")
public class AccountSecret
{
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "account_code")
    private String accountCode;

    @TableField(value = "account_secret")
    private String accountSecret;

    @TableField(value = "daily_status")
    private Integer dailyStatus;

    public Integer getId() {
        return id;
    }

    public AccountSecret setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public AccountSecret setAccountCode(String accountCode) {
        this.accountCode = accountCode;
        return this;
    }

    public String getAccountSecret() {
        return accountSecret;
    }

    public AccountSecret setAccountSecret(String accountSecret) {
        this.accountSecret = accountSecret;
        return this;
    }

    public Integer getDailyStatus() {
        return dailyStatus;
    }

    public AccountSecret setDailyStatus(Integer dailyStatus) {
        this.dailyStatus = dailyStatus;
        return this;
    }
}
