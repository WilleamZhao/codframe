package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 字段注解
 *
 * @author sourcod
 * @version 1.0
 * @className CodField
 * @date 2019/6/4 4:35 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CodField {

    /**
     * 列名称
     */
    String name() default "";

    /**
     * 数据库类型
     * @return
     */
    String type() default "";

    /**
     * 是否允许为空
     * @return
     */
    boolean isNull() default true;

    /**
     * 默认值
     * @return
     */
    String def() default "";

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

}
