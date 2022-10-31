package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fixme ColumnsIndex 索引目录，主要在创建数据库表和更新数据库表，起到一定的作用
 * @author Ariel.
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomIndex {
    String name() default "";

    String columnList();

    boolean unique() default false;
}
