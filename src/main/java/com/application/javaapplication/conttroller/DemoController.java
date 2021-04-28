package com.application.javaapplication.conttroller;

import com.application.javaapplication.redis.RedisConfig;
import com.application.javaapplication.redis.RedisUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import com.application.javaapplication.redis.RedisConfig;

@Controller
public class DemoController
{

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request) {
        String inputKey = request.getParameter("abd");

        return "Index Page : out put : " + inputKey;
    }

    @ResponseBody
    @RequestMapping("/post_test")
    public String postTest(HttpServletRequest request) {
        String postKey = request.getParameter("post");

        return "Post key 123 : " + postKey;
    }

    @RequestMapping("/redis_test")
    @ResponseBody
    public String redisStr(HttpServletRequest request)
    {
        String message = "";

        message = redisUtil.get("bu");

        return "redis Message : " + message;
    }
}
