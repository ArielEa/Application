package com.application.javaapplication.tools.dosql;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class ColumnListUtils {
    /**
     * 将旧对象的属性值与新对象的属性值做比较 记录修改的属性值名称
     * @param oldObject 旧对象
     * @return 修改的对象属性值名称集合
     */
    public List<String> getChangeList(Object oldObject)
    {
        List<String> changeList = new ArrayList<>();

        String[] filedNameArray = getFieldName(oldObject);

        for(int j=0 ; j< filedNameArray.length ; j++) {
            //遍历所有属性
            String fieldName = filedNameArray[j];
            //获取属性的名字
            changeList.add(fieldName);
        }
        return changeList;
    }


    /**
     * 作用::获取属性名数组
     * fixme filedClass().getDeclaredFields()
     */
    private String[] getFieldName(Object o)
    {
        Field[] fields=o.getClass().getDeclaredFields();

        String[] fieldNames=new String[fields.length];

        for (int i=0; i<fields.length; i++) {
            fieldNames[i]=fields[i].getName();
        }

        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     * @param fieldName 属性名称
     * @param o 对象
     * @return
     */
    private Object getFieldValueByName(String fieldName, Object o) throws Exception
    {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();

            String getter = "get" + firstLetter + fieldName.substring(1);

            Method method = o.getClass().getMethod(getter, new Class[] {});

            Object value = method.invoke(o, new Object[] {});

            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
