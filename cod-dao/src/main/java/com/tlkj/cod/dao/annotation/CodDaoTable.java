package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 表名注解
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoTable
 * @date 2019/6/4 5:11 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CodDaoTable {

    String name();

    /**
     * 引擎
     * 默认 INNODB
     */
    String engine() default "";

    /**
     * 编码方式
     */
    String charset() default "utf8";

    /**
     * 备注
     */
    String comment() default "";

    /**
     * 是否删除从建
     */
    boolean drop() default false;

}
