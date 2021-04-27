package com.application.javaapplication.entity;

public class User
{
    private int id;

    private String owner_code;

    private String admin_name;

    private int user_identity;

    private int user_status;

    private String register_time;

    private String update_time;

    private String login_time;

    private String login_out;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_code() {
        return owner_code;
    }

    public void setOwner_code(String owner_code) {
        this.owner_code = owner_code;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public int getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(int user_identity) {
        this.user_identity = user_identity;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogin_out() {
        return login_out;
    }

    public void setLogin_out(String login_out) {
        this.login_out = login_out;
    }
}
