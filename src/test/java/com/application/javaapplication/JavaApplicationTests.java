package com.application.javaapplication;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class JavaApplicationTests {

    @Test
    void contextLoads() {
        String url = "jdbc:mysql://127.0.0.1:3306/project?serverTimezone=GMT%2B8&useSSL=false";
        String username = "root";
        String password = "eternal673770";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(url, username, password);
            System.out.println("连接远程数据库成功");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("连接远程数据库失败");
            e.printStackTrace();
        }
    }

}
