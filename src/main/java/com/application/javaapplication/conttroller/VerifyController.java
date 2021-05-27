package com.application.javaapplication.conttroller;

import com.application.javaapplication.entity.AccountSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.application.javaapplication.tools.verify.verifyConfig;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VerifyController
{
    @Value("${SYSTEM_SECRET}")
    public String SystemSecret;

    @Autowired
    private verifyConfig verifyConfig;

    public void verifyUserSecret(String AccountCode) throws Exception
    {
        Map<String, String> secretMap = new HashMap<String, String>() {{
            put("account_code", AccountCode);
            put("account_secret", SystemSecret.trim());
        }};

        AccountSecret accountSecret = verifyConfig.verifySecret(secretMap);

        if (accountSecret.getAccountSecret() == null) {
            throw new Exception("invalid");
        }
        if (accountSecret.getAccountSecret().equals("")) {
            throw new Exception("invalid");
        }
    }
}
