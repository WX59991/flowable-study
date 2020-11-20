package com.study.flowable.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于记录方法的入参和返回
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAnnotation {

    /**
     * 返回值是否是void
     * @return true--返回值是void  false--返回值不是void
     */
    boolean isVoid() default false;

    /**
     * 是否需要校验，需要校验的把bindingResult作为最后一个入参
     * @return
     */
    boolean isVerfyUtil() default false;
}
