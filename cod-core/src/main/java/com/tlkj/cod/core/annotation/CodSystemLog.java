/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 系统日志
 *
 * @author sourcod
 * @version 1.0
 * @className CodSystemLog
 * @date 2018/11/6 下午2:06
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CodSystemLog {

    /**
     * 操作名称
     */
    String name() default "";

    /**
     * 操作类型
     */
    String type() default "";

    /**
     * 是否打印日志
     * 默认不打印
     */
    boolean isLog() default false;

}
