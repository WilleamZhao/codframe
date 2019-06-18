package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc cod-dao 事物管理
 * 目前只支持单数据源(默认数据源&指定数据源)
 * 多数据源请使用代码方式
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoTransaction
 * @date 2019/6/18 11:12 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CodDaoTransaction {

    /**
     * 数据源名称
     * 默认为默认数据源
     * @return
     */
    String name() default "";

}
