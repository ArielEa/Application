package com.application.javaapplication.annotationCustomer;

import com.application.javaapplication.entity.Orders;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@Component
public class AnnotationUtil
{
    public <T> Map customTableFields(Class<T> element)
    {
        // 表名、索引、字段部分
        CustomTable customTable = element.getAnnotation(CustomTable.class);

        String CustomTableName = customTable.name();
        CustomIndex[] CustomIndexes = customTable.indexes();

        List<customIndex> columnIndexClass = Lists.newArrayList();
        for (CustomIndex customIndex: CustomIndexes) {
            columnIndexClass.add(
                    new customIndex().setName(customIndex.name())
                            .setUnique(customIndex.unique())
                            .setColumnList(customIndex.columnList())
            );
        }
        Map<String, customIndex> customIndexSet = columnIndexClass.stream().collect(
                Collectors.toMap(customIndex::getName, Function.identity())
        );
        // 字段属性部分解释
        Field[] fields = element.getDeclaredFields();

        List<customTableColumns> singleFields = Lists.newArrayList();

        for ( Field field : fields ) {
            CustomTableFields customTableFields = field.getAnnotation(CustomTableFields.class);

            singleFields.add(
                    new customTableColumns()
                            .setOrmId(customTableFields.OrmId())
                            .setStrategy(customTableFields.strategy())
                            .setPrimaryType(customTableFields.primaryType())
                            .setName(customTableFields.name())
                            .setType(customTableFields.type())
                            .setLength(customTableFields.length())
                            .setUnique(customTableFields.unique())
                            .setCustomId(customTableFields.customId())
                            .setComment(customTableFields.comment())
                            .setNullable(customTableFields.nullable())
                            .setDefaultValue(customTableFields.defaultValues())
            );
        }

//        Map<String, customTableColumns> customColumnSet = singleFields.stream().collect(
//                Collectors.toMap(customTableColumns::getName, Function.identity())
//        );

        Map<String, String> customColumnSet = singleFields.stream().collect(
                Collectors.toMap(customTableColumns::getName, customTableColumns::getType, (key1, key2 ) ->
                        {
                            return key1;
                        }
                )
        );
        System.out.println( customColumnSet );

//        TableName table = Orders.class.getAnnotation(TableName.class);
//        String tableName = table.value();
//        System.out.println(tableName);
//
//        CustomTableFields field = Orders.class.getAnnotation(CustomTableFields.class);

        return new HashMap();
    }

    @lombok.Data
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class customTableColumns {
        public boolean ormId;

        public String customId;

        public String strategy;

        public boolean primaryType;

        public String name;

        public String type;

        public int length;

        public boolean nullable;

        public boolean unique;

        public String comment;

        public boolean defaultValue;
    }

    @lombok.Data
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class customIndex
    {
        private String name;

        private String columnList;

        private boolean unique;
    }

    public static Map<Integer, String> unsetByValue(Map<Integer, String> mapTest, String value)
    {
        mapTest.entrySet().removeIf((e) -> e.getValue() == value);

        return mapTest;
    }

    public static Map<Integer, String> unsetByKey(Map<Integer, String> mapTest, int key)
    {
        mapTest.entrySet().removeIf((e) -> e.getKey() == key);

        return mapTest;
    }
}
