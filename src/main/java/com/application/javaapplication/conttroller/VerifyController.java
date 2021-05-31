package com.application.javaapplication.conttroller;

import com.application.javaapplication.entity.AccountSecret;
import com.application.javaapplication.entity.User;
import com.application.javaapplication.tools.Contains;
import com.application.javaapplication.tools.dosql.SearchExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.application.javaapplication.tools.verify.verifyConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VerifyController
{
    @Value("${SYSTEM_SECRET}")
    public String SystemSecret;

    @Autowired
    private verifyConfig verifyConfig;

    @Autowired
    private SearchExtends EM;

    public void verifyUserSecret(String AccountCode) throws Exception
    {
        Map<Integer, String> SelectColumn = new HashMap<Integer, String>(){{
            put(2, "A.id");
            put(0, "A.account_code");
            put(1, "A.account_secret");
            put(3, "A.daily_status");
        }};
        try {
            List<AccountSecret> accountSecrets = null;

            accountSecrets = EM.getTableName(AccountSecret.class).createQueryBuilder("A")
                    .select(SelectColumn)
                    .where("A.account_code", Contains.EM_EQ, "account_code")
                    .andWhere("A.account_secret", Contains.EM_EQ, "account_secret")
                    .setParameter("account_code", AccountCode)
                    .setParameter("account_secret", "CERTEERQQQ312321311YY")
                    .orderBy("A.id", "DESC")
                    .getQuery().getResult(MyRowMapper());

            System.out.println(accountSecrets);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("end --------------- ");


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
