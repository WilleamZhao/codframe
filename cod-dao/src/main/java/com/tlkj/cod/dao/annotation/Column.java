/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 列字段
 *
 * @author sourcod
 * @version 1.0
 * @className Column
 * @date 2019/1/4 8:06 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {

    /**
     * 列名称
     */
    String cName() default "";

    /**
     * 表名称
     */
    String tName() default "";

    /**
     * 别名
     */
    String aliasName() default "";

}
