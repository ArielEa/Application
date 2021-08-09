package com.application.javaapplication.annotationCustomer;

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
//        try {
//            GroovyScriptEngine engine = new GroovyScriptEngine(
//                    "src/main/java/com/application/javaapplication/Commands/hello.groovy"
//            );
//            Script script = engine.createScript("hello.groovy", new Binding());
//
//            String res = (String) script.invokeMethod("hello", 2);
//
//            System.out.println( res );
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        testMap();

        // 表名、索引、字段部分
        CustomTable customTable = element.getAnnotation(CustomTable.class);

        String CustomTableName = customTable.name();
        CustomIndex[] CustomIndexes = customTable.indexes();

        System.out.println(CustomTableName);

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

        System.out.println(customIndexSet);

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

        Map<String, customTableColumns> customColumnSet = singleFields.stream().collect(
                Collectors.toMap(customTableColumns::getName, Function.identity())
        );

//        Map<String, String> customColumnSet = singleFields.stream().collect(
//                Collectors.toMap(customTableColumns::getName, customTableColumns::getType, (key1, key2 ) ->
//                        {
//                            return key1;
//                        }
//                )
//        );

//        System.out.println( customColumnSet );

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

    public void testMap()
    {
        Map<Integer, String> mapTest = new HashMap<Integer, String>(){{
                put(1, "value 1");
                put(2, "value 2");
                put(3, "value 3");
            }};
//        mapTest = unsetByValue(mapTest, "value 1");
        unsetByKey(mapTest, 1);
        System.out.println(mapTest);
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
