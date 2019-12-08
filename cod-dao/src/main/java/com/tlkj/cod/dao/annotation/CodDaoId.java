package com.tlkj.cod.dao.annotation;

import com.tlkj.cod.dao.model.enums.CodDaoDatabaseIdTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc cod-dao 主键注解
 * 多个只有第一个生效
 *
 * 不设默认第一字段为ID
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoId
 * @date 2019/6/18 3:10 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CodDaoId {

    /**
     * 主键名称
     * 默认id
     */
    String name() default "id";

    /**
     * 主键类型
     * 默认UUID
     */
    CodDaoDatabaseIdTypeEnum type() default CodDaoDatabaseIdTypeEnum.UUID;
}
