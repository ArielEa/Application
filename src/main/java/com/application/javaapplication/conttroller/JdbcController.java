package com.application.javaapplication.conttroller;

import com.application.javaapplication.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/jdbc")
public class JdbcController
{
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/user")
    @ResponseBody
    public Map<String, List> list(ModelMap map)
    {
        String sql = "Select * from admin_user";

        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;
            Map<String, String> adminUser = null;
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAdmin_name(resultSet.getString("admin_name"));
                user.setLogin_out(resultSet.getString("login_out"));
                return user;
            }
        });
        Map<String, List> mapList = new HashMap<String, List>() {
            {
                put("admin_user_info", userList);
            }
        };
        return mapList;
    }
}
