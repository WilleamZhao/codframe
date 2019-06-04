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
 * @className CodTable
 * @date 2019/6/4 5:11 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CodTable {

    String name();

    /**
     *
     * @return
     */
    String engine() default "";

    /**
     * 编码方式
     * @return
     */
    String charset() default "utf8";

    /**
     * 备注
     * @return
     */
    String comment() default "";

    /**
     * 是否删除
     * @return
     */
    boolean drop() default false;

}
