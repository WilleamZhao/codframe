/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 条件
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoViewWhere
 * @date 2019/1/7 6:18 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface CodDaoViewWhere {

    String[] value();

}
