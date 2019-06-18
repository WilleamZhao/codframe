/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc 视图注解
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoView
 * @date 2019/1/4 7:27 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CodDaoView {

    /**
     * 视图名称
     */
    String name() default "";

    /**
     * 是否是逻辑视图
     */
    boolean isLogic() default true;
}
