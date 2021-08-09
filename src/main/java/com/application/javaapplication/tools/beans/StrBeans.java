package com.application.javaapplication.tools.beans;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 拆分字符专用工具箱，不允许做其他操作
 * @author Ariel.
 */
@Service("str.split.service")
public class StrBeans
{
    @Transactional
    public String splitStr(Map<String, String> List, String BlockStr) throws Exception
    {
        List<String> keys = new ArrayList<String>(List.keySet());

        Collections.sort(keys);

        StringBuilder preStr = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String Key = keys.get(i);

            String Value = List.get(Key);

//            Value = URLEncoder.encode(Value, "UTF-8");

            preStr.append(Key).append(" = ").append("'").append(Value).append("'");

            if (i != keys.size() - 1) {
                preStr.append(BlockStr);
            }
        }
        return preStr.toString();
    }
}
