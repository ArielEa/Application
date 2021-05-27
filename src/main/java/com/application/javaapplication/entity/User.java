package com.application.javaapplication.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("admin_user")
public class User
{
    @TableField(value = "id")
    private int id;

    @TableField(value = "owner_code")
    private String ownerCode;

    @TableField(value = "adminName")
    private String adminName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "user_identity")
    private int userIdentity;

    @TableField(value = "user_status")
    private int userStatus;

    @TableField(fill = FieldFill.INSERT, value = "register_time")
    private Timestamp registerTime;

    @TableField(fill = FieldFill.UPDATE, value = "update_time")
    private Timestamp updateTime;

    @TableField(fill = FieldFill.UPDATE, value = "login_time")
    private Timestamp loginTime;

    @TableField(value = "login_out")
    private Timestamp loginOut;

    @TableField(value = "token")
    private String token;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public User setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
        return this;
    }

    public String getAdminName() {
        return adminName;
    }

    public User setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public User setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public User setUserStatus(int userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public User setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public User setUpdateTime(Timestamp upTimestamp) {
        this.updateTime = upTimestamp;
        return this;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public User setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public Timestamp getLoginOut() {
        return loginOut;
    }

    public User setLoginOut(Timestamp loginOut) {
        this.loginOut = loginOut;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }
}