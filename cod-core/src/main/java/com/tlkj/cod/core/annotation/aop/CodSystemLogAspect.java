/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.annotation.aop;

import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.common.constant.CodCommonParamDefined;
import com.tlkj.cod.core.annotation.CodSystemLog;
import com.tlkj.cod.core.service.AspectSystemLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Desc 系统日志切面
 *
 * @author sourcod
 * @version 1.0
 * @className SystemLogAspect
 * @date 2018/12/6 11:06 AM
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
public class CodSystemLogAspect {

    @Autowired
    @Lazy
    private AspectSystemLogService logService;

    @Pointcut("@annotation(com.tlkj.cod.core.annotation.CodSystemLog) && execution(* com.tlkj.cod.action..* (..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        String methodName = joinPoint.getSignature().getName();
        Method method = methodSignature.getMethod();

        // 参数名称
        String[] params = methodSignature.getParameterNames();

        // 参数值
        Object[] args = joinPoint.getArgs();

        CodSystemLog log = method.getAnnotation(CodSystemLog.class);
        String token = request.getParameter(CodCommonParamDefined.ADMIN_TOKEN_NAME);

        /*String username =(String) session.getAttribute("username");
        if(StringUtils.isBlank("username")){
            username = " - ";
        }*/

        // 获取ip
        String ip = CodCommonNetWork.getIpAddress(request);
        // 返回值
        Object o = joinPoint.proceed();

        logService.saveSystemLog(methodName, "syslog", ip,"username", log.name(), log.type(), params, args, o);
        return o;

    }
}
