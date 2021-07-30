package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTable
{
    String TablePrefix() default "product_api";

    String name() default "";

    CustomIndex[] indexes() default {};
}
