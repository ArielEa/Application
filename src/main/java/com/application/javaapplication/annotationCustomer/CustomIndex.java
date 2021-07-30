package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomIndex {
    String name() default "";

    String columnList();

    boolean unique() default false;
}
