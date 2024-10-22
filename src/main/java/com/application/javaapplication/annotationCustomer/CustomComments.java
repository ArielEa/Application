package com.application.javaapplication.annotationCustomer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomComments
{
    String comment() default "";

    String values() default "";
}

