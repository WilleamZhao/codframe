/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.config.annotation;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 配置属性注入
 * 配合@Value使用
 *
 * @author sourcod
 * @version 1.0
 * @className CodValue
 * @date 2019/4/9 11:01 AM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CodValue {

    /**
     * 配置名称
     */
    String name() default "";

    /**
     * 配置类型
     * 默认内置数据库
     * 支持data、数据库、缓存、配置文件、apollo等
     *
     */
    CodConfigSourceType type() default CodConfigSourceType.DATA;

}
