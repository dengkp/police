package com.zz.police.common.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * @author dengkp
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";

}
