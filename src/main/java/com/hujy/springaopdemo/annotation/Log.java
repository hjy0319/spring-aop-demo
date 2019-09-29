package com.hujy.springaopdemo.annotation;

import java.lang.annotation.*;

/**
 * 记录日志注解
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-29 11:00
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
