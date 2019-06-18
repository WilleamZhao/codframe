package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 列注解
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoColumn
 * @date 2019/6/4 4:35 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CodDaoColumn {

    /**
     * 列名称
     * 为空默认取字段名
     */
    String name() default "";

    /**
     * 数据库类型
     */
    String type();

    /**
     * 是否允许为空
     * @return boolean
     */
    boolean isNull() default true;

    /**
     * 默认值
     */
    String def() default "";

    /**
     * 编码方式
     */
    String charset() default "utf8";

    /**
     * 备注
     */
    String comment() default "";

}
