/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc Log日志切面
 *
 * @author sourcod
 * @version 1.0
 * @className Log
 * @date 2018/10/30 下午8:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {

    /**
     * 方法名称
     */
    String name() default "";

    /**
     * 是否打印返回值; 默认打印
     * 为了解决返回值过多问题
     */
    boolean isBack() default true;

    /**
     * 日志类型
     */
    String type() default "clog";
}
