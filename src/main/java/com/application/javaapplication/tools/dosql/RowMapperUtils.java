package com.application.javaapplication.tools.dosql;

import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.annotationCustomer.CustomTable;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class RowMapperUtils
{
    @Autowired
    private AnnotationUtil annotationUtil;

    @Autowired
    private ColumnsResultSetUtils columnsResultSetUtils;

    public <T> RowMapper<T> getRowMapper(Class<?> element) throws Exception {
        Map<String, String> elementColumns = annotationUtil.customTableFields(element);

        String className = element.getAnnotation(CustomTable.class).name();

        className = className.substring(0, 1).toUpperCase()+className.substring(1);

        Class<?> OriginEntityClass = Class.forName("com.application.javaapplication.entity." + className);

        return createCommonRowMapper(OriginEntityClass, elementColumns);
    }

    public <OriginEntityClass> RowMapper<OriginEntityClass> createCommonRowMapper(Class<?> OriginEntityClass, Map<String, String> elementColumns)
            throws Exception {
        return new RowMapper<OriginEntityClass>() {
            OriginEntityClass object = null;

            @SneakyThrows
            @Override
            public OriginEntityClass mapRow(ResultSet resultSet, int i) throws SQLException {

                object = (OriginEntityClass) OriginEntityClass.newInstance();

                Method MethodKey = null;

                for ( Map.Entry<String, String > entityData : elementColumns.entrySet() )
                {
                    String PreviewMethodKey = reString(entityData.getKey());

                    String keyAnalysisValue = columnsResultSetUtils.getResultSetValues(entityData.getValue());

                    switch (keyAnalysisValue) {
                        case "string":
                            MethodKey = OriginEntityClass.getDeclaredMethod(PreviewMethodKey, String.class);

                            MethodKey.setAccessible(true);
                            MethodKey.invoke(object, resultSet.getString(entityData.getKey()));

                            break;
                        case "float":
                            MethodKey = OriginEntityClass.getDeclaredMethod(PreviewMethodKey, Float.class);

                            MethodKey.setAccessible(true);

                            MethodKey.invoke(object, resultSet.getFloat(entityData.getKey()));
                            break;
                        case "int":
                        case "smallint":
                            MethodKey = OriginEntityClass.getDeclaredMethod(PreviewMethodKey, Integer.class);

                            MethodKey.setAccessible(true);
                            MethodKey.invoke(object, resultSet.getInt(entityData.getKey()));
                            break;
                        case "timestamp":
                            MethodKey = OriginEntityClass.getDeclaredMethod(PreviewMethodKey, Date.class);
                            MethodKey.setAccessible(true);
                            MethodKey.invoke(object, resultSet.getTimestamp(entityData.getKey()));
                            break;
                        case "text":
                            MethodKey = OriginEntityClass.getDeclaredMethod(PreviewMethodKey, Long.class);
                            MethodKey.setAccessible(true);
                            MethodKey.invoke(object, resultSet.getLong(entityData.getKey()));
                            break;
                        default:
                            throw new Exception("error");
                    }
                }
                return object;
            }
        };
    }

    private static String reString(String orgStr)
    {
        if (StringUtils.isBlank(orgStr)) {
            return orgStr;
        }

        if (!orgStr.contains("_")) {
            return reSetString(orgStr);
        }

        String[] splitArr = orgStr.split("_");
        String change = "";
        StringBuilder newStr = new StringBuilder(splitArr[0]);

        for (int i = 1; i < splitArr.length; i++) {
            String substring = splitArr[i].substring(0, 1);
            change = splitArr[i].replaceFirst(substring, substring.toUpperCase(Locale.ROOT));
            newStr.append(change);
        }

        return reSetString(newStr.toString());
    }

    private static String reSetString(String resetStr)
    {
        return "set" + resetStr.substring(0, 1) .toUpperCase() + resetStr.substring(1);
    }
}
