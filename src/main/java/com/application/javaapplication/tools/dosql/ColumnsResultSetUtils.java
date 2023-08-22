package com.application.javaapplication.tools.dosql;

import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ColumnsResultSetUtils {
    Map<String, String> defaultColumnsCompare = new HashMap<String, String>() {
        {
            put("int", "int");
            put("smallint", "int");
            put("string", "string");
            put("datetime", "timestamp");
            put("text", "long");
            put("float", "float");
        }
    };

    private String matchValue;

    public String getResultSetValues(String key)
    {
        for (Map.Entry<String, String> column: defaultColumnsCompare.entrySet()) {

            if (key.equals(column.getKey())) {
                matchValue = column.getValue();
            }
        }

        return matchValue;
    }
}
