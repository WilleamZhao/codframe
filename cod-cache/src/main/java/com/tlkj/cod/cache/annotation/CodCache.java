/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc codFrame缓存注解
 *
 * @author sourcod
 * @version 1.0
 * @className CodCache
 * @date 2019/2/13 1:53 PM
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
public @interface CodCache {

    /**
     * key
     */
    String key();

    /**
     * value
     */
    String value() default "";

    /**
     * 类型默认ehcache
     */
    String type() default "ehcache";
}
