package com.eruekaclient.demo.config.annotation;

import java.lang.annotation.*;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会过滤字段
 *
 * Created by 7winkle on 2017/4/20.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONS.class)
public @interface JSON {

    Class<?> type();
    String include() default "";
    String filter() default "";

}
