package com.application.javaapplication.login.utils;

import org.skife.jdbi.v2.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import com.application.javaapplication.tools.dosql.dosqlUtils;

// 更新登录信息的组件，功能，工具
@Component("login.jdbc.action")
public class UpdateLoginInfoUtils
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Boolean updateLogin( Map<String, String> list, Map<String, String> where) throws Exception
    {
        if (list.isEmpty()) {
            throw new Exception("ERROR");
        }
        dosqlUtils dosqlUtils = (dosqlUtils) applicationContext.getBean("global.sql.action");

        dosqlUtils.UpdateData("admin_user", list, where);

        return true;
    }
}
