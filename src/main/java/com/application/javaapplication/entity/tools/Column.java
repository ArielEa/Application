package com.application.javaapplication.entity.tools;

import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service(value = "custom_table_fields")
public class Column
{
    @Transactional
    public <T> Map<Integer, String> customTableFields(Class<T> element, String alias)
    {
        Map<String, AnnotationUtil.customTableColumns> fields = explainCustomFields(element, alias);

        Map<Integer, String> tableFiles = new HashMap<>();

        int i = 0;
        for ( Map.Entry<String, AnnotationUtil.customTableColumns> field: fields.entrySet() ) {
            String key = field.getKey();
            tableFiles.put( i, alias + "." + key );
            i++;
        }
        return tableFiles;
    }

    protected <T> Map<String, AnnotationUtil.customTableColumns> explainCustomFields( Class<T> element, String alias )
    {
        Field[] fields = element.getDeclaredFields();

        List<AnnotationUtil.customTableColumns> singleFields = Lists.newArrayList();

        for ( Field field : fields ) {
            CustomTableFields customTableFields = field.getAnnotation(CustomTableFields.class);

            singleFields.add(
                    new AnnotationUtil.customTableColumns()
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

        Map<String, AnnotationUtil.customTableColumns> customColumnSet = singleFields.stream().collect(
                Collectors.toMap(AnnotationUtil.customTableColumns::getName, (key) -> {
                    return key;
                })
        );
        return customColumnSet;
    }
}
