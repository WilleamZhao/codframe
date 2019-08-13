/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.config.annotation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodValueAspect
 * @date 2019/4/9 11:08 AM
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
public class CodValueAspect {

    /**
     * 切 service和facade层
     */
    @Pointcut("@annotation(com.tlkj.cod.config.annotation.CodValue)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("asd");
        return joinPoint.proceed();
    }
}
