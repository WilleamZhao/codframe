/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc cod-dao 列字段
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoViewColumn
 * @date 2019/1/4 8:06 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CodDaoViewColumn {

    /**
     * 列名称
     * 为空默认主表 model 字段
     */
    String cName() default "";

    /**
     * 表名称
     * 为空默认主表表名
     */
    String tName() default "";

    /**
     * 是否是字段
     */
    boolean isName() default true;

    /**
     * 别名
     */
    String aliasName() default "";

}
