package com.zz.police.common.annotation;

import java.lang.annotation.*;

/**
 * rest接口不需要授权注解
 * @author dengkp
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestAnon {
}
