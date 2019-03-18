/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.annotation.aop;

import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.core.annotation.SystemLog;
import com.tlkj.cod.core.service.AspectLogService;
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
import java.text.SimpleDateFormat;

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
public class SystemLogAspect {

    @Autowired
    @Lazy
    private AspectLogService logService;

    @Pointcut("@annotation(com.tlkj.cod.core.annotation.SystemLog) && execution(* com.tlkj.cod.action..* (..))")
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

        SystemLog log = method.getAnnotation(SystemLog.class);
        String token = request.getHeader("access_token");

        /*String username =(String) session.getAttribute("username");
        if(StringUtils.isBlank("username")){
            username = " - ";
        }*/

        // 打印开始日志
        if (log.isLog()){
            //格式化开始时间
            String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            logService.saveLog("clog","进入" + log.name() + log.type() + "方法, 开始时间: " + startTime + "; 参数: ", params, args);
        }

        // 获取ip
        String ip = CodCommonNetWork.getIpAddress(request);
        // 返回值
        Object o = joinPoint.proceed();

        // 打印结束日志
        if (log.isLog()){
            //格式化结束时间
            String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            logService.saveLog("clog", "退出" + log.name() + log.type() + "方法, 结束时间: " + endTime + "; 返回信息: ", o);
        }
        logService.saveSystemLog(methodName, "syslog", ip,"username", log.name(), log.type(), params, args, o);
        return o;

    }
}
