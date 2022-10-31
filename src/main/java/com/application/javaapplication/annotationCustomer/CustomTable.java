package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTable
{
    String TablePrefix() default "product_api";

    String name() default "";

    String comment() default "";

    // 默认是配置文件中的 doctrine.prefix.alias
    String doctrinePrefixAlias() default "";

    CustomIndex[] indexes() default {};
}
