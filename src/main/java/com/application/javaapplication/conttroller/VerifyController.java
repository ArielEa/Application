package com.application.javaapplication.conttroller;

import com.application.javaapplication.entity.AccountSecret;
import com.application.javaapplication.tools.Contains;
import com.application.javaapplication.tools.dosql.SqlDoctrineExtends;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.application.javaapplication.tools.verify.verifyConfig;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@ResponseBody
public class VerifyController
{
    @Value("${SYSTEM_SECRET}")
    public String SystemSecret;

    @Autowired
    private verifyConfig verifyConfig;

    @Autowired
    private SqlDoctrineExtends EM;

    @Autowired
    private HttpServletRequest request;

    public void init( HttpServletRequest request )
    {
        String token = request.getHeader("project_token");
    }


//    @PostConstruct
    public <k, v> Map<k, v> checkAuth( HttpServletRequest request)
    {
        Map<Integer, String> returnList = new HashMap<>();

        String token = request.getHeader("project_token");

        returnList.put(1, token);

        return (Map<k, v>) returnList;
    }

    public void verifyUserSecret(String AccountCode) throws Exception
    {
        Map<Integer, String> SelectColumn = new HashMap<Integer, String>(){{
            put(2, "A.id");
            put(0, "A.account_code");
            put(1, "A.account_secret");
            put(3, "A.daily_status");
        }};

        showConfigBeans();
        try {
            List<AccountSecret> accountSecrets = null;

            String Alias = "A";

            // entityManager  实体管理
            accountSecrets = EM.getTableName( AccountSecret.class )
                    .createQueryBuilder( Alias )
                    .select( SelectColumn )
                    .where(Alias + ".account_code", Contains.EM_EQ, "iden")
                    .setParameter("iden", AccountCode)
//                    .andWhere(Alias + ".account_secret", Contains.EM_EQ, "secret")
//                    .setParameter("secret", "testS")
                    .orderBy(Alias + ".id", "DESC")
                    .getQuery().getResult(MyRowMapper());

            System.out.println("查询结果:");
            System.out.println(accountSecrets);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
            public AccountSecret mapRow(ResultSet resultSet, int i) throws SQLException
            {
                accountSecret = new AccountSecret()
                        .setAccountSecret( resultSet.getString("account_secret") )
                        .setId( resultSet.getInt("id") )
                        .setDailyStatus( resultSet.getInt("daily_status"))
                        .setAccountCode( resultSet.getString("account_code") );

                return accountSecret;

            }
        };
    }

    public static void showConfigBeans() {
        List<CloudColumnConfigBeans> maps = Lists.newArrayList(
                new CloudColumnConfigBeans().setId("1").setShow("2"),
                new CloudColumnConfigBeans().setId("11").setShow("22"),
                new CloudColumnConfigBeans().setId("111").setShow("222")
        );

        Map<String, String> SingleLists = maps.stream().collect(
                Collectors.toMap(CloudColumnConfigBeans::getId, CloudColumnConfigBeans::getShow, (key1, key2 ) ->
                        {
                            return key1;
                        }
                )
        );

        Map<String, CloudColumnConfigBeans> CloudColumnSet = maps.stream().collect(
                Collectors.toMap(CloudColumnConfigBeans::getId, Function.identity())
        );

        Map<String, String> newList = maps.stream().collect(
                Collectors.toMap(CloudColumnConfigBeans::getId, (e) -> e.getShow(), (k, k1) ->{
                    return k;
                })
        );

        System.out.println("显示结果：");
        System.out.println(JSONObject.toJSONString(newList));
        System.out.println("显示结果结束：");

        System.out.println(JSONObject.toJSONString(SingleLists));
        System.out.println(JSONObject.toJSONString(CloudColumnSet));
    }


    @Setter
    @Getter
    @Accessors(chain = true)
    @lombok.Data
    public static class CloudColumnConfigBeans {

        public String id;

        public String show;
    }
}
