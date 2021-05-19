package com.application.javaapplication.enums;

import com.application.javaapplication.redis.RedisUtil;
import io.lettuce.core.RedisException;
import net.minidev.json.JSONObject;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class CommonEnum
{
    @Autowired
    private RedisUtil redisUtil;

    private Map<String, String> mapList;

    public Boolean setEnumMap()
    {
        Reflections reflections = new Reflections();

        Set<Class<? extends BaseEnum>> monitorClasses = reflections.getSubTypesOf(BaseEnum.class);

//        System.out.println("size:"+monitorClasses.size());

        Map<String, Map> mapList = new HashMap<String, Map>();
        for (Class<? extends BaseEnum> m : monitorClasses) {
            try {
//                System.out.println("name:"+m.getSimpleName());
                BaseEnum[] enumConstants = m.getEnumConstants();

                for (BaseEnum anEnum : enumConstants) {
                    Map<Integer, String> enumList = new HashMap<>();
                    enumList.put(anEnum.getCode(), anEnum.getDisplayName());
                    mapList.put(anEnum.toString(), enumList);
//                    System.out.println("enumName:"+anEnum+"  value:"+anEnum.getDisplayName()+"  name:"+anEnum.getCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println(mapList.toString());

        String jsonObject = JSONObject.toJSONString(mapList);

        try {
            redisUtil.set("enums_columns", jsonObject); // 存储是会自动序列化
        }catch (RedisException re) {
            return false; // no callback message
        }

        return true;
    }
}
