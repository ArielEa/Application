package com.application.javaapplication.login.utils;

import com.application.javaapplication.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.application.javaapplication.tools.dosql.dosqlEnums;
import com.application.javaapplication.tools.dosql.dosqlUtils;

@Component
public class LoginJdbcUtils {
    @Autowired
    private ApplicationContext applicationContext;

    public List<User> getLoginInfo(String username) throws Exception
    {
        Map<String, String> adminCondition = new HashMap<String, String>(){{
            put("owner_code", username);
        }};
        dosqlUtils dosqlUtils = (dosqlUtils) applicationContext.getBean("global.sql.action");

        List<User> userList = null;

        userList = dosqlUtils.Search("admin_user", adminCondition, MyRowMapper());

        return userList;
    }

    private RowMapper<User> MyRowMapper()
    {
        return new RowMapper<User>() {
            User user = null;
            @Override
            public User mapRow(ResultSet resultSet, int i)
                    throws SQLException
            {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAdminName(resultSet.getString("admin_name"));
                user.setRegisterTime(resultSet.getTimestamp("register_time"));
                user.setPassword(resultSet.getString("password"));
                user.setUpdateTime(resultSet.getTimestamp("update_time"));
                user.setLoginOut(resultSet.getTimestamp("login_out"));
                user.setLoginTime(resultSet.getTimestamp("login_time"));
                user.setOwnerCode(resultSet.getString("owner_code"));
                user.setToken(resultSet.getString("token"));
                return user;
            }
        };
    }
}
