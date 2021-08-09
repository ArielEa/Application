package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.*;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTableFields
{
    boolean OrmId() default false;

    String customId() default ""; // 如果自定义就是别的字段作为id，默认id

    String strategy() default ""; // AUTO 如果是ID主键写AUTO

    boolean primaryType() default false;

    String name() default "";

    String type(); // smallint/int/text/string/boolean/datetime

    int length() default 0;

    boolean nullable() default false;

    boolean unique() default false;

    String comment() default "";

    boolean defaultValues() default false;
}
