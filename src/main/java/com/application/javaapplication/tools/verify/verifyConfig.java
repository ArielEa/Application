package com.application.javaapplication.tools.verify;
;
import com.application.javaapplication.entity.AccountSecret;
import com.application.javaapplication.tools.dosql.dosqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class verifyConfig
{
    @Autowired
    private ApplicationContext applicationContext;

    public AccountSecret verifySecret(Map<String, String> secretCondition)
            throws Exception
    {
        AccountSecret accountSecret = null;

        dosqlUtils dosqlUtils = (dosqlUtils) applicationContext.getBean("global.sql.action");

        List<AccountSecret> accountSecrets = null;

        accountSecrets = dosqlUtils.Search("account_secret", secretCondition, MyRowMapper());

        if (accountSecrets.size() == 0) {
            throw new Exception("数据不存在");
        }
        if (accountSecrets.size() > 1) {
            throw new Exception("重复的给予码");
        }
        accountSecret = accountSecrets.get(0);

        return accountSecret;
    }

    public RowMapper<AccountSecret> MyRowMapper()
    {
        return new RowMapper<AccountSecret>() {
            AccountSecret accountSecret = null;
            @Override
            public AccountSecret mapRow(ResultSet resultSet, int i)
                    throws SQLException
            {
                accountSecret = new AccountSecret();
                accountSecret.setAccountSecret( resultSet.getString("account_secret") );
                accountSecret.setId( resultSet.getInt("id") );
                accountSecret.setDailyStatus( resultSet.getInt("daily_status"));
                accountSecret.setAccountCode( resultSet.getString("account_code") );
                return accountSecret;
            }
        };
    }
}
