package com.application.javaapplication.enums;

import com.alibaba.fastjson.JSON;
import com.application.javaapplication.redis.RedisUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Ref;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

@Component
public class EnumsUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TestUtil testUtil;

    private String EnumColumns = "enums_columns";

    private Map<String, Map> enumsMapList;

    /**
     * 优先设置redis连接
     * @return
     */
    public EnumsUtil enumsMap()
    {
        String list = redisUtil.get(EnumColumns);

        Map<String, String> enumsList;

        enumsList = JSON.parseObject(list, Map.class);

        Map<String, Map> MapList = new HashMap<String, Map>();

        Iterator<Map.Entry<String, String>> entryIterator = enumsList.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entryIterator.next();

            String key = entry.getKey().toString();

            String value = entry.getValue().toString();

            Map<String, String> valueList = JSON.parseObject(value, Map.class);

            MapList.put(key, valueList);
        }
        enumsMapList = MapList;

        return this;
    }

    public String getTest()
    {

        System.out.println(enumsMapList);

        return "AAAAAA";
    }
}
